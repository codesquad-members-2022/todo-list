package com.team26.todolist.exception;

public class EmptyCardStatusException extends RuntimeException {
    public EmptyCardStatusException(String message) {
        super(message);
    }
}
