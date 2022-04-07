package com.example.backend.controller.exception;

import org.springframework.http.HttpStatus;

public interface BaseExceptionType {
    String getErrorCode();

    String getMessage();

    HttpStatus getHttpStatus();
}
