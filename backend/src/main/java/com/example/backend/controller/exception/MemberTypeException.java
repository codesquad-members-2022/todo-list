package com.example.backend.controller.exception;


import org.springframework.http.HttpStatus;

public enum MemberTypeException implements BaseExceptionType {

    NOT_FOUND_USER(1, "사용자를 찾을 수 없습니다.", HttpStatus.BAD_REQUEST),
    DUPLICATE_USER(2, "이미 존재하는 사용자입니다.", HttpStatus.BAD_REQUEST),
    WRONG_PASSWORD(3, "비밀번호를 잘못 입력하였습니다.", HttpStatus.UNAUTHORIZED);

    private final int errorCode;
    private final String message;
    private final HttpStatus httpStatus;

    MemberTypeException(int errorCode, String message, HttpStatus httpStatus) {
        this.errorCode = errorCode;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    @Override
    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
