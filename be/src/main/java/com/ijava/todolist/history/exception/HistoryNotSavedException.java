package com.ijava.todolist.history.exception;

import org.springframework.http.HttpStatus;

public class HistoryNotSavedException extends HistoryRuntimeException {

    public HistoryNotSavedException() {
        super("활동기록을 저장하는데에 실패하였습니다.");
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
