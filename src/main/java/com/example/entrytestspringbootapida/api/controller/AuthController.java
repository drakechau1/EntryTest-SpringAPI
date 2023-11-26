package com.example.entrytestspringbootapida.api.controller;

import com.example.entrytestspringbootapida.api.model.LoginRequest;
import com.example.entrytestspringbootapida.api.model.LoginResponse;
import com.example.entrytestspringbootapida.api.security.JWTIssuer;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final JWTIssuer jwtIssuer;

    @PostMapping("/auth/login")
    public LoginResponse login(@RequestBody @Validated LoginRequest loginRequest) {
        String token = jwtIssuer.IssueToken(1L, loginRequest.getUsername(), List.of("USER"));
        return new LoginResponse(token);
    }
}
