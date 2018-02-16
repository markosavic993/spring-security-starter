package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Created by msav on 12/29/2017.
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping(value = {"/", "/login"})
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping(value="/registration")
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @PostMapping(value = "/registration")
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());

        if(userExists != null) {
            bindingResult.rejectValue("email", "error.user", "There is already user registered with provided email.");
        }

        if(bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been successfully registered.");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("login");
        }

        return modelAndView;
    }

    @GetMapping(value = "/admin/home")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ModelAndView adminHome() {
        ModelAndView modelAndView = new ModelAndView();
        User user = getAuthenticatedUser();
        modelAndView.addObject("username", "Welcome " + user.getFirstName() + " " + user.getLastName());
        modelAndView.addObject("adminMessage", "Content available only for users with admin role");
        modelAndView.setViewName("admin/home");

        return modelAndView;
    }

    @GetMapping(value = "user/home")
    public ModelAndView userHome() {
        ModelAndView modelAndView = new ModelAndView();
        User user = getAuthenticatedUser();
        modelAndView.addObject("username", "Welcome " + user.getFirstName() + " " + user.getLastName());
        modelAndView.addObject("userMessage", "Content available for logged in users");
        modelAndView.setViewName("user/home");

        return modelAndView;
    }

    @GetMapping(value = "/access-denied")
    public ModelAndView denyAccess() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/access-denied");

        return modelAndView;
    }

    private User getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userService.findUserByEmail(auth.getName());
    }


}
