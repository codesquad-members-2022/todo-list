package com.todolist.exception;

import lombok.Getter;

@Getter
public class RestResponse {
    private String errorFiledName;
    private String errorMessage;

    private RestResponse(String errorFiledName, String errorMessage) {
        this.errorFiledName = errorFiledName;
        this.errorMessage = errorMessage;
    }

    // MethodArgumentNotValidException 예외 처리
    public static RestResponse methodArgumentNotValidException(String errorFiledName, String errorMessage) {
        return new RestResponse(errorFiledName, errorMessage);
    }

    // MethodArgumentTypeMismatchException 예외 처리
    public static RestResponse methodArgumentTypeMismatchException(String errorFiledName, String errorMessage) {
        return new RestResponse(errorFiledName, errorMessage);
    }

    // NotFoundCardException 예외 처리
    public static RestResponse notFoundCardException(String errorFiledName, String errorMessage) {
        return new RestResponse(errorFiledName, errorMessage);
    }
}
