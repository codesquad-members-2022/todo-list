package com.todolist.domain;

import lombok.Getter;

@Getter
public class User {
    private Integer userId;
    private String password;
    private boolean removed;

    public User(Integer userId, String password, boolean removed) {
        this.userId = userId;
        this.password = password;
        this.removed = removed;
    }
}
