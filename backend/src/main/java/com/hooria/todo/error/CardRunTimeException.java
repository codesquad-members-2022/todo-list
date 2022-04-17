package com.hooria.todo.error;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CardRunTimeException extends RuntimeException {

    private final ErrorCode errorCode;

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
