package com.todolist.exception;

import org.springframework.http.ResponseEntity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GlobalException extends RuntimeException {

    private final ExceptionType exceptionType;

    public ResponseEntity<String> getResponse() {
        return exceptionType.getResponse();
    }
}

