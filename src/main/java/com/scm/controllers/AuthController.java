package com.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.scm.entities.User;
import com.scm.helpers.Message;
import com.scm.helpers.MessageType;
import com.scm.repositories.UserRepo;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepo userRepo;

    // verify email
    @GetMapping("/verify-email")
    public String verifyEmail(@RequestParam("token") String token, HttpSession session) {
        User user = userRepo.findByEmailToken(token).orElse(null);
        if (user != null) {
            if (user.getEmailToken().equals(token)) {
                user.setEmailVerified(true);
                user.setEnabled(true);
                userRepo.save(user);
                session.setAttribute("message",
                Message.builder()
                        .content("your Email is verified. You can login now")
                        .type(MessageType.green)
                        .build());
                return "success_page";
            }
            session.setAttribute("message",
                Message.builder()
                        .content("Email not verified ! Token isn't associated with user")
                        .type(MessageType.red)
                        .build());
            return "error_page";
        }

        session.setAttribute("message",
                Message.builder()
                        .content("Email not verified !! Token isn't associated with user")
                        .type(MessageType.red)
                        .build());

        return "error_page";
    }

}
