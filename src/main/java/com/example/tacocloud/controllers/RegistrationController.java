package com.example.tacocloud.controllers;

import com.example.tacocloud.models.RegistrationForm;
import com.example.tacocloud.repositories.JpaUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private JpaUserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public RegistrationController(JpaUserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String registrationForm(){
        return "registration";
    }

    @PostMapping
    public String processRegistration(RegistrationForm registrationForm){
        userRepository.save(registrationForm.toUser(passwordEncoder));
        return "redirect:/login";
    }
}
