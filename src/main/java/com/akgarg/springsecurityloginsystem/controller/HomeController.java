package com.akgarg.springsecurityloginsystem.controller;

import com.akgarg.springsecurityloginsystem.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String main() {
        return "redirect:home";
    }

    @RequestMapping("/home")
    public String home() {
        return "home";
    }

    @RequestMapping("/login")
    public String login() {
        // checking if user is login or not
        // if user is logged in then redirect to the user dashboard and didn't allow to sign up until user logouts
        if (isUserLoggedIn()) {
            return "redirect:/user/dashboard";
        }
        return "login";
    }


    // method to check if user is logged in or not
    private boolean isUserLoggedIn() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails;
    }

    @RequestMapping("/signup")
    public String signup(Model model) {
        // checking if user is login or not
        // if user is logged in then not allowing to sign up
        if (isUserLoggedIn()) {
            return "redirect:/user/dashboard";
        }
        model.addAttribute("user", new User());
        return "signup";
    }
}