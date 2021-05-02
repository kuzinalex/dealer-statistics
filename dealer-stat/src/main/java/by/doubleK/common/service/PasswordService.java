package by.doubleK.common.service;

import by.doubleK.common.entity.PasswordResetToken;
import by.doubleK.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class PasswordService {

    private EmailService emailService;
    private RedisService redisService;
    private PasswordEncoder passwordEncoder;
    private UserService userService;

    @Autowired
    public PasswordService(EmailService emailService, UserService userService, RedisService redisService, PasswordEncoder passwordEncoder) {
        this.emailService = emailService;
        this.redisService = redisService;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    public boolean resetPassword(User user) {
        User existingUser = (userService.getByEmail(user.getEmail()));
        if (existingUser != null && existingUser.isActivated()) {
            emailService.sendSimpleMessage(existingUser.getEmail(), existingUser.getUsername(), "reset password");
            return true;
        } else {
            return false;
        }
    }

    public boolean updatePassword(PasswordResetToken passwordResetToken) {
        passwordResetToken.setPassword(passwordEncoder.encode(passwordResetToken.getPassword()));
        String username = redisService.getValueFromRedis(passwordResetToken.getSecurityCode());
        User user = userService.getByUsername(username);
        if (user != null) {
            user.setPassword(passwordResetToken.getPassword());
            userService.saveUser(user);
            return true;
        } else {
            return false;
        }
    }
}
