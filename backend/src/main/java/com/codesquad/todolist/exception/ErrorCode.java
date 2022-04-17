package com.codesquad.todolist.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    CARD_NOT_FOUND(HttpStatus.NOT_FOUND, "카드를 찾을 수 없습니다."),
    COLUMN_NOT_FOUND(HttpStatus.NOT_FOUND, "컬럼을 찾을 수 없습니다."),
    HISTORY_NOT_FOUND(HttpStatus.NOT_FOUND, "히스토리를 찾을 수 없습니다."),
    NEXT_CARD_NOT_FOUND(HttpStatus.NOT_FOUND, "다음 카드를 찾을 수 없습니다."),
    ILLEGAL_CARD_ORDER(HttpStatus.INTERNAL_SERVER_ERROR, "순서 조건을 만족하는 카드가 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;

    ErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }
}
