package com.ijava.todolist.user.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends UserRuntimeException {

    public UserNotFoundException() {
        super("사용자를 찾을 수 없습니다.");
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
