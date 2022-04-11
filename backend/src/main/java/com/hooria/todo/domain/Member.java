package com.hooria.todo.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Member {

    private long id;
    private String userId;
    private String password;
    private String name;

    public static Member of(long id, String userId, String password, String name) {
        return new Member(id, userId, password, name);
    }

    public static Member of(String userId, String password, String name) {
        return new Member(0, userId, password, name);
    }

    public static Member of(String userId, String password) {
        return new Member(0, userId, password, null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return id == member.id && Objects.equals(userId, member.userId) && Objects.equals(password, member.password) && Objects.equals(name, member.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, password, name);
    }
}
