package com.ijava.todolist.card.exception;

import com.ijava.todolist.exception.GlobalRuntimeException;

public abstract class CardRuntimeException extends GlobalRuntimeException {
    public CardRuntimeException(String message) {
        super(message);
    }
}
