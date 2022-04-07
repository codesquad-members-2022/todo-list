package com.ijava.todolist.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private final int statusCode;
    private final String statusName;
    private final String message;
}
