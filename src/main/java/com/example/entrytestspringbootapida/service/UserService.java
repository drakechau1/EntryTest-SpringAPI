package com.example.entrytestspringbootapida.service;

import com.example.entrytestspringbootapida.api.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private static final String EXISTING_USERNAME = "duccm";
    public Optional<User> FindByUsername(String username) {

        if (!EXISTING_USERNAME.equalsIgnoreCase(username)) return Optional.empty();

        // Mock test
        var user = new User();
        user.setUserId(1L);
        user.setUsername(EXISTING_USERNAME);
        user.setRole("admin");
        // using https://bcrypt-generator.com/ to generate encrypted password
        user.setPassword("$2a$12$Q8/cbp9dsoYem3YAJETcxePCYvpXeVqGhFf8Y2ZkmJPyLjjZoek6e"); // duccm
        return Optional.of(user);
    }
}
