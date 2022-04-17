package com.todolist.dto;

public enum Action {
    CREATION("등록"),
    MOVEMENT("이동"),
    DELETION("삭제"),
    MODIFICATION("변경");

    private final String action;

    Action(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }
}
