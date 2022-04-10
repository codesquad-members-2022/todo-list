package com.example.backend.controller.exception;

import org.springframework.http.HttpStatus;

public enum CardTypeException implements BaseExceptionType {

    ;



    @Override
    public int getErrorCode() {
        return 0;
    }

    @Override
    public String getMessage() {
        return null;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return null;
    }
}
