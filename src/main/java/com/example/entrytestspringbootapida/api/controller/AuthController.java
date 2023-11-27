package com.example.entrytestspringbootapida.api.controller;

import com.example.entrytestspringbootapida.api.model.LoginRequest;
import com.example.entrytestspringbootapida.api.model.LoginResponse;
import com.example.entrytestspringbootapida.api.security.JWTIssuer;
import com.example.entrytestspringbootapida.api.security.UserPrincipal;
import com.example.entrytestspringbootapida.api.security.UserPrincipalAuthenticationToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final JWTIssuer jwtIssuer;
    private final AuthenticationManager authenticationManager;

    // Authorization using username, password to generate token FAILED

//    @PostMapping("/auth/login")
//    public LoginResponse login(@RequestBody @Validated LoginRequest loginRequest) {
//        var auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(auth);
//
//        var principal = (UserPrincipal) auth.getPrincipal();
//
//        var roles = principal.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .toList();
//
//        String token = jwtIssuer.IssueToken(principal.getUserId(), principal.getUsername(), List.of("user"));
//        return new LoginResponse(token);
//    }

    // Authorize to get Token
    @PostMapping("/auth/login")
    public LoginResponse login(@RequestBody @Validated LoginRequest loginRequest) {
        String token = jwtIssuer.IssueToken(1L, loginRequest.getUsername(),List.of("user"));
        return new LoginResponse(token);
    }
}
