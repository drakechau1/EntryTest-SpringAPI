package com.example.entrytestspringbootapida.api.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JWTIssuer {
    private final JWTProperties jwtProperties;

    public String IssueToken(Long userId, String username, List<String> roles) {
        return JWT.create()
                .withSubject(String.valueOf(userId))
                .withExpiresAt(Instant.now().plus(Duration.of(7, ChronoUnit.DAYS)))
                .withClaim("username", username)
                .withClaim("roles", roles)
                .sign(Algorithm.HMAC256(jwtProperties.getSecretKey()));
    }
}
