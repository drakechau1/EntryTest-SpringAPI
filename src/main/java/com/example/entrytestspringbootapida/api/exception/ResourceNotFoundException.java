package com.example.entrytestspringbootapida.api.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "ResourceNotFoundException: " + getMessage();
    }
}
