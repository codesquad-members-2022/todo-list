package com.hooria.todo.domain;

import java.util.Arrays;

public enum Action {

    UNKNOWN(0), ADD(1), REMOVE(2), UPDATE(3), MOVE(4);

    private final int code;

    Action(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static Action of(int code) {
        return Arrays.stream(Action.values())
                .filter(d -> d.code == code)
                .findFirst()
                .orElse(UNKNOWN);
    }

    public static Action of(String device) {
        try {
            return Action.valueOf(device);
        } catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }
}
