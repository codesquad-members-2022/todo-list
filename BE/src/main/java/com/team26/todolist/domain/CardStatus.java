package com.team26.todolist.domain;

public enum CardStatus {
    TODO("해야할 일"),
    ONGOING("하고 있는 일"),
    DONE("완료한 일");

    private String name;
스
    CardStatus(String name) {
        this.name = name;
    }
}
