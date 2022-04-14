package com.hooria.todo.error;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    ADD_CARD_ERROR("C001", HttpStatus.INTERNAL_SERVER_ERROR, "카드 생성에 실패했습니다."),
    DELETE_CARD_ERROR("C002", HttpStatus.INTERNAL_SERVER_ERROR, "카드 삭제에 실패했습니다."),
    UPDATE_CARD_ERROR("C003", HttpStatus.INTERNAL_SERVER_ERROR, "카드 수정에 실패했습니다.");

    final String code;
    final HttpStatus status;
    final String message;

    ErrorCode(String code, HttpStatus status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
