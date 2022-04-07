package com.example.backend.controller.exception;

public class ErrorResult {

    private int code;
    private String message;

    public ErrorResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    static ErrorResult create(BaseExceptionType baseExceptionType) {
        return new ErrorResult(baseExceptionType.getErrorCode(), baseExceptionType.getMessage());
    }
}
