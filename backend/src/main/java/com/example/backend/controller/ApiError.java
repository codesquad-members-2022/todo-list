package com.example.backend.controller;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.http.HttpStatus;

public class ApiError {

    @ApiModelProperty(value = "오류 메시지", required = true)
    private final String message;

    @ApiModelProperty(value = "HTTP 오류코드", required = true)
    private final int status;

    ApiError(Throwable throwable, HttpStatus status) {
        this(throwable.getMessage(), status);
    }

    ApiError(String message, HttpStatus status) {
        this.message = message;
        this.status = status.value();
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

}
