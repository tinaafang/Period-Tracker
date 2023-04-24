package com.example.onlineordering.service;

import com.example.onlineordering.ConfirmationToken;
import com.example.onlineordering.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class RegistrationService {

    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    public String register(User user) {
//        boolean isValidEmail = emailValidator.
//                test(request.getEmail());
//
//        if (!isValidEmail) {
//            throw new IllegalStateException("email not valid");
//        }

        String token = userService.signUpUser(user);




        String link = "http://localhost:8090/api/registration/confirm?token=" + token;

        emailService.sendEmail("tinafang114@gmail.com","surprise!","<p>Hi there :D,<p><br><p>Click the link below to activate your account:<p><br><a href=http://localhost:8090/api/registration/confirm?token="+token+">activate</a><br><p>link will expire in 15 minutes");

        return token;
    }



    @Transactional
    public void confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiredAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        userService.enableAppUser(
                confirmationToken.getUser().getEmail());
    }
}
