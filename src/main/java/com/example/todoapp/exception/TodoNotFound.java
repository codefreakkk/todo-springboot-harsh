package com.example.todoapp.exception;

public class TodoNotFound extends RuntimeException {
    public TodoNotFound() {
        super();
    }

    public TodoNotFound(String message) {
        super(message);
    }
}
