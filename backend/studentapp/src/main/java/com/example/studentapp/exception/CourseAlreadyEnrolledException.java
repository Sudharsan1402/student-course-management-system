package com.example.studentapp.exception;

public class CourseAlreadyEnrolledException extends RuntimeException {

    public CourseAlreadyEnrolledException(String message) {
        super(message);
    }
}