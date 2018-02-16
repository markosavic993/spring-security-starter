package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by msav on 2/16/2018.
 */

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/home")
    public ModelAndView userHome() {
        ModelAndView modelAndView = new ModelAndView();
        User user = getAuthenticatedUser();
        modelAndView.addObject("username", "Welcome " + user.getFirstName() + " " + user.getLastName());
        modelAndView.addObject("userMessage", "Content available for logged in users");
        modelAndView.setViewName("user/home");

        return modelAndView;
    }

    private User getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userService.findUserByEmail(auth.getName());
    }

}
