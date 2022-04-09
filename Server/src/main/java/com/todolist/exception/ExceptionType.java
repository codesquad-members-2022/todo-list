package com.todolist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionType {

    EXAMPLE(HttpStatus.NOT_FOUND, "등록되지 않은 card Id입니다.");

    private final HttpStatus httpStatus;
    private final String message;

    public ResponseEntity<String> getResponse() {
        return new ResponseEntity<>(message, httpStatus);
    }
}
