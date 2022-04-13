package com.todolist.exception;

import lombok.Getter;

@Getter
public class RestResponse {
    private final Object errorMessage;

    private RestResponse(Object errorMessage) {
        this.errorMessage = errorMessage;
    }

    public static RestResponse of(Object errorMessage) {
        return new RestResponse(errorMessage);
    }
}
