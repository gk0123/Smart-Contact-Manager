package com.scm.helpers;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class Helper {

    public static String getEmailOfLoggedInUser(Authentication authentication) {
        if (authentication instanceof OAuth2AuthenticationToken) {
            var oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
            var clientId = oAuth2AuthenticationToken.getAuthorizedClientRegistrationId();
            var oauth2User = (OAuth2User) authentication.getPrincipal();
            String username = "";

            if ("google".equalsIgnoreCase(clientId)) {
                // Get email if logged in with Google
                System.out.println("Getting email for Google");
                username = oauth2User.getAttribute("email");
            } else if ("github".equalsIgnoreCase(clientId)) {
                // Get email if logged in with GitHub
                System.out.println("Getting email for GitHub");
                String email = oauth2User.getAttribute("email");
                if (email != null) {
                    username = email;
                } else {
                    String login = oauth2User.getAttribute("login");
                    if (login != null) {
                        username = login + "@gmail.com";
                    } else {
                        System.out.println("No email or login attribute found for GitHub user");
                    }
                }
            }
            return username;
        } else {
            // Get email if logged in with local database
            System.out.println("Getting email from local database");
            return authentication.getName();
        }
    }
}
