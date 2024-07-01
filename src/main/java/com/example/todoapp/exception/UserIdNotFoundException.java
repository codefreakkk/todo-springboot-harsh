package com.example.todoapp.exception;

public class UserIdNotFoundException extends RuntimeException {
    public UserIdNotFoundException() {
        super();
    }

    public UserIdNotFoundException(String message) {
        super(message);
    }
}
