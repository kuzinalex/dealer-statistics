package by.doubleK.common.controller;

import by.doubleK.common.entity.Role;
import by.doubleK.common.entity.User;
import by.doubleK.common.service.EmailService;
import by.doubleK.common.service.RoleService;
import by.doubleK.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;

@Controller
public class RegistrationController {

    private EmailService emailService;
    private PasswordEncoder passwordEncoder;
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public RegistrationController(UserService userService, RoleService roleService,
                                  EmailService emailService, PasswordEncoder passwordEncoder) {
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.roleService = roleService;
    }


    @PostMapping("/registration")
    public ResponseEntity<User> registration(@RequestBody User user) {
        if (userService.getByUsername(user.getUsername()) != null | userService.getByEmail(user.getEmail()) != null | user.getUsername().contains(" ")) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setCreatedAt(new Date().toString());
            userService.saveUser(user);
            emailService.sendSimpleMessage(user.getEmail(), user.getUsername(), "registration");
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }


    @GetMapping("/users/{username}/auth/confirm/{code}")
    public ResponseEntity<User> checkCode(@PathVariable String code, @PathVariable String username) {
        if (emailService.activateAccount(username, code)) {
            User user = userService.getByUsername(username);
            Role role = roleService.getByName("ROLE_USER");
            user.getRoles().add(role);
            user.setActivated(true);
            userService.saveUser(user);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}