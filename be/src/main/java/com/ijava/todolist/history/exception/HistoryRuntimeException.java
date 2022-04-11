package com.ijava.todolist.history.exception;

import com.ijava.todolist.exception.GlobalRuntimeException;

public abstract class HistoryRuntimeException extends GlobalRuntimeException {

    public HistoryRuntimeException(String message) {
        super(message);
    }
}
