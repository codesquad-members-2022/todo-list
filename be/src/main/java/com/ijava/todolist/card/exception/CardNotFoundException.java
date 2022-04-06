package com.ijava.todolist.card.exception;

import org.springframework.http.HttpStatus;

public class CardNotFoundException extends CardRuntimeException {

    public CardNotFoundException() {
        super("카드를 찾을 수 없습니다");
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
