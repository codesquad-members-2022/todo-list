package com.hooria.todo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Member {

    private long id;
    private String userId;
    private String password;
    private String name;

    public static Member of(long id, String userId, String password, String name) {
        return new Member(id, userId, password, name);
    }

    public static Member of(String userId, String password) {
        return new Member(0, userId, password, null);
    }
}
