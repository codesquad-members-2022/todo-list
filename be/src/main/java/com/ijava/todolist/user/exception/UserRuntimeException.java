package com.ijava.todolist.user.exception;

import com.ijava.todolist.exception.GlobalRuntimeException;

public abstract class UserRuntimeException extends GlobalRuntimeException {

    public UserRuntimeException(String message) {
        super(message);
    }
}
