package com.ijava.todolist.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = GlobalRuntimeException.class)
    public ResponseEntity<String> handleGlobalRuntimeException(GlobalRuntimeException e) {
        return new ResponseEntity<>(e.getStatus());
    }
}
