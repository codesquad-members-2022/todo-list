package com.hooria.todo.domain;

import java.util.Arrays;

public enum Status {

    NONE(0), TODO(1), IN_PROGRESS(2), DONE(3);

    private final int code;

    Status(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static Status of(int code) {
        return Arrays.stream(Status.values())
                .filter(s -> s.code == code)
                .findFirst()
                .orElse(NONE);
    }

    public static Status of(String status) {
        try {
            return Status.valueOf(status);
        } catch (IllegalArgumentException e) {
            return NONE;
        }
    }
}
