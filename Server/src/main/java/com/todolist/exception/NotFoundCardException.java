package com.todolist.exception;

public class NotFoundCardException extends GlobalException {

    private final String errorFieldName;

    public NotFoundCardException(ExceptionType exceptionType, String errorFieldName) {
        super(exceptionType);
        this.errorFieldName = errorFieldName;
    }

    public String getErrorFieldName() {
        return errorFieldName;
    }
}

