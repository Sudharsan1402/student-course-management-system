package com.example.studentapp.exception;

public class SeatsOverException extends RuntimeException {

    public SeatsOverException(String message) {
        super(message);
    }
}