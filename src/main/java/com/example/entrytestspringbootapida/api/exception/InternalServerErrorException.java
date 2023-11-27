package com.example.entrytestspringbootapida.api.exception;

public class InternalServerErrorException extends RuntimeException{
    public InternalServerErrorException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "InternalServerErrorException: " + getMessage();
    }
}
