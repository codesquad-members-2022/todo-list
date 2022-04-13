package com.todolist.exception;

public class NotFoundCardException extends RuntimeException {

    private final ExceptionType exceptionType;
    private final String errorFiledName;

    public NotFoundCardException(ExceptionType exceptionType, String errorFiledName) {
        this.exceptionType = exceptionType;
        this.errorFiledName = errorFiledName;
    }

    public String getExceptionType() {
        return exceptionType.getMessage();
    }

    public String getErrorFiledName() {
        return errorFiledName;
    }
}

