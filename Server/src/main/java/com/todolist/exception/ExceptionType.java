package com.todolist.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ExceptionType {

    NO_COOKIE(HttpStatus.NOT_FOUND, "쿠키가 없습니다. 사용자 정보를 먼저 요청하세요."),
    NO_USER_ID_IN_COOKIE(HttpStatus.NOT_FOUND, "Cookie 에 사용자 정보가 없습니다."),
    NO_FOUND_CARD(HttpStatus.BAD_REQUEST, "해당 Card 를 찾을 수 없습니다. Card 번호를 확인해주세요."),
    CARD_TYPE_MISMATCH(HttpStatus.BAD_REQUEST, "cardId 타입이 맞지 않습니다. Card 번호를 확인해주세요.");

    private final HttpStatus httpStatus;
    private final String message;
}
