package com.todolist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionType {

    NO_COOKIE(HttpStatus.NOT_FOUND, "쿠키가 없습니다. 사용자 정보를 먼저 요청하세요."),
    NO_USER_ID_IN_COOKIE(HttpStatus.NOT_FOUND, "Cookie에 사용자 정보가 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;

    public ResponseEntity<String> getResponse() {
        return new ResponseEntity<>(message, httpStatus);
    }
}
