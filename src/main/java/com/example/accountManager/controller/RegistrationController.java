package com.example.accountManager.controller;

import com.example.accountManager.model.RegistrationRequest;
import com.example.accountManager.entity.User;
import com.example.accountManager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public String registerUser(@RequestBody RegistrationRequest registrationRequest) {
        if (userRepository.findByUsername(registrationRequest.getUsername()) != null) {
            return "Username already exists!";
        }
        User user = new User();
        user.setUsername(registrationRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        user.setEnabled(true);
        userRepository.save(user);
        return "User registered successfully!";
    }
}
