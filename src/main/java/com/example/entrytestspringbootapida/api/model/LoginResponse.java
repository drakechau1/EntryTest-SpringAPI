package com.example.entrytestspringbootapida.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private final String accessToken;

    public LoginResponse(String token) {
        accessToken = token;
    }
}
