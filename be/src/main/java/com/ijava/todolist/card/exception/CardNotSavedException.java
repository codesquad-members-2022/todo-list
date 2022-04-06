package com.ijava.todolist.card.exception;

import org.springframework.http.HttpStatus;

public class CardNotSavedException extends CardRuntimeException {
    public CardNotSavedException() {
        super("카드를 저장하는데 실패하였습니다.");
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
