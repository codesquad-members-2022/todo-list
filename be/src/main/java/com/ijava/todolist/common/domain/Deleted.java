package com.ijava.todolist.common.domain;

import java.util.Arrays;

public enum Deleted {
    Y, N
    ;

    public static Deleted from(String name) {
        return Arrays.stream(values())
                .filter(d -> d.name().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Deleted 상태를 읽을 수 없습니다."));
    }
}
