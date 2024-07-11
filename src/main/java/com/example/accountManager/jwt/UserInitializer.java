package com.example.accountManager.jwt;

import com.example.accountManager.entity.User;
import com.example.accountManager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserInitializer {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository) {
        return args -> {
            if (userRepository.findByUsername("testuser") == null) {
                User user = new User();
                user.setUsername("testuser");
                user.setPassword(passwordEncoder.encode("testpassword"));  // Encode the password
                user.setEnabled(true);
                userRepository.save(user);
            }
        };
    }
}
