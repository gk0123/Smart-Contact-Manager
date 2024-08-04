package com.scm.config;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuthAuthenticationSucessHandler implements AuthenticationSuccessHandler {

    Logger logger = LoggerFactory.getLogger(OAuthAuthenticationSucessHandler.class);

    @Override
    public void onAuthenticationSuccess(
        HttpServletRequest request, 
        HttpServletResponse response,
        Authentication authentication) throws IOException, ServletException {
        // TODO Auto-generated method stub
        logger.info("OAuthAuthenticationSucessHandler");

        DefaultOAuth2User user = (DefaultOAuth2User)authentication.getPrincipal();
        //database save
        logger.info(user.getName());

        user.getAttributes().forEach((key, value)->{
            logger.info("{} => {}", key, value);
        });
        logger.info(user.getAuthorities().toString());

        new DefaultRedirectStrategy().sendRedirect(request, response, "/user/profile");
    }
}
