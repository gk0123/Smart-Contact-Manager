package com.scm.helpers;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Helper {

    private static final Logger logger = LoggerFactory.getLogger(Helper.class);

    public static String getEmailOfLoggedInUser(Authentication authentication) {
        if (authentication == null) {
            logger.warn("Authentication object is null");
            return null;
        }

        if (authentication instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken oauth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
            String clientId = oauth2AuthenticationToken.getAuthorizedClientRegistrationId();
            OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
            String username = "";

            if ("google".equalsIgnoreCase(clientId)) {
                logger.info("Getting email from Google");
                username = oauth2User.getAttribute("email");
            } else if ("github".equalsIgnoreCase(clientId)) {
                logger.info("Getting email from GitHub");
                username = oauth2User.getAttribute("email") != null 
                        ? oauth2User.getAttribute("email") 
                        : oauth2User.getAttribute("login") + "@gmail.com";
            }

            return username;
        } else {
            logger.info("Getting data from local database");
            return authentication.getName();
        }
    }

    public static String getLinkForEmailVerification(String emailToken) {
        return String.format("http://localhost:8081/auth/verify-email?token=%s", emailToken);
    }
}
