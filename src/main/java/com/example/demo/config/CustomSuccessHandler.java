package com.example.demo.config;

import com.example.demo.model.Roles;
import com.example.demo.service.CustomUserPrinciple;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by msav on 2/5/2018.
 */
// TODO: 2/6/2018 Rename according to real project requirements
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    private static final String LOGGED_IN_USER = "logged_in_user";
    private static final String AUTHORITIES = "authorities";
    private static final String ADMIN_HOME_PATH = "/admin/home";
    private static final String USER_HOME_PATH = "/user/home";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        HttpSession session = httpServletRequest.getSession();

        addLoggedInUserInfoToSession(authentication, session);

        httpServletResponse.setStatus(HttpServletResponse.SC_OK);

        if(userHasAdminRole()) {
            httpServletResponse.sendRedirect(ADMIN_HOME_PATH);
        } else {
            httpServletResponse.sendRedirect(USER_HOME_PATH);
        }
    }

    private boolean userHasAdminRole() {
        return getLoggedInUserData().getAuthorities().contains(new SimpleGrantedAuthority(Roles.ADMIN.getValue()));
    }

    private CustomUserPrinciple getLoggedInUserData() {
        return (CustomUserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    private void addLoggedInUserInfoToSession(Authentication authentication, HttpSession session) {
        CustomUserPrinciple authUser = getLoggedInUserData();
        session.setAttribute(LOGGED_IN_USER, authUser.getUsername());
        session.setAttribute(AUTHORITIES, authentication.getAuthorities());
    }
}
