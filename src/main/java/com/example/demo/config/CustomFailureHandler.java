package com.example.demo.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by msav on 2/6/2018.
 */
public class CustomFailureHandler implements AuthenticationFailureHandler {

    private static final String LOGIN_ERROR_PATH = "/login?error=true";

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        httpServletResponse.sendRedirect(LOGIN_ERROR_PATH);
    }
}
