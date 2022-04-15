package com.todolist.domain;

import lombok.Getter;

@Getter
public class User {
    private final Integer userId;
    private final String password;
    private final boolean removed;

    public User(Integer userId, String password, boolean removed) {
        this.userId = userId;
        this.password = password;
        this.removed = removed;
    }
}
