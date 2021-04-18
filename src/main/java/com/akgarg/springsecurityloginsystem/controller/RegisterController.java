package com.akgarg.springsecurityloginsystem.controller;

import com.akgarg.springsecurityloginsystem.entity.User;
import com.akgarg.springsecurityloginsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class RegisterController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public RegisterController(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/register")
    public String registerUserForm(@Valid @ModelAttribute User user, BindingResult result, Model model, HttpSession session) {
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "signup";
        }
        try {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            user.setRole("ROLE_USER");

            user.setEnabled(true);
            user.setAccountNonExpired(true);
            user.setAccountNonLocked(true);
            user.setCredentialsNonExpired(true);

            model.addAttribute("user", user);
            this.userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("user", user);
        }

        session.setAttribute("successRegister", "User registered successful");
        return "redirect:login";
    }
}
