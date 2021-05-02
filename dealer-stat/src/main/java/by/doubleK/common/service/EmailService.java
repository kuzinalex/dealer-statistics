package by.doubleK.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmailService {

    private final JavaMailSender emailSender;
    private RedisService redisService;


    @Autowired
    public EmailService(@Qualifier("getMailSender") JavaMailSender emailSender,
                        RedisService redisService) {

        this.emailSender = emailSender;
        this.redisService = redisService;
    }


    public void sendSimpleMessage(String to, String username, String whichMessage) {
        SimpleMailMessage message = new SimpleMailMessage();

        UUID securityCode = UUID.randomUUID();
        String securityCodeAsString = securityCode.toString();
        String text = generateMessage(whichMessage, username, securityCodeAsString);

        message.setFrom("dealer-statistics@yandex.by");
        message.setTo(to);
        message.setSubject("Activation code");
        message.setText(text);
        emailSender.send(message);
        if (whichMessage.equals("registration")) {
            redisService.addCodeToRedis(username, securityCodeAsString);
        } else {
            redisService.addCodeToRedis(securityCodeAsString, username);
        }
    }


    private String generateMessage(String whichMessage, String username, String securityCodeAsString) {
        if (whichMessage.equals("registration")) {
            return String.format("Hello, " + username + "! \n" +
                    "Welcome to Dealer Stat. Please, visit next link: http://localhost:8081/users/%s/auth/confirm/%s", username, securityCodeAsString);
        } else {
            return String.format("Hello, " + username + "! \n" +
                    "Welcome to Dealer Stat. This is your password reset code: %s", securityCodeAsString);
        }
    }

    public boolean activateAccount(String key, String value) {
        return redisService.checkCodeFromRedis(key, value);
    }

}


