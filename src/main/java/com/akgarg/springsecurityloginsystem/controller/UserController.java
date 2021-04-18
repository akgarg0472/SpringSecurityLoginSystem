package com.akgarg.springsecurityloginsystem.controller;

import com.akgarg.springsecurityloginsystem.entity.User;
import com.akgarg.springsecurityloginsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping("/dashboard")
    public String dashboard(Principal principal, Model model) {
        User user = this.userRepository.getUserByEmail(principal.getName());

        if (user != null) {
            model.addAttribute("user", user);
            return "user/dashboard";
        } else {
            return "login";
        }
    }
}
