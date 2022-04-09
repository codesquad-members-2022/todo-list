package com.hooria.todo.domain;

import java.util.Arrays;

public enum Device {

    NONE(0), WEB(1), IOS(2), ANDROID(3);

    private final int code;

    Device(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static Device of(int code) {
        return Arrays.stream(Device.values())
            .filter(d -> d.code == code)
            .findFirst()
            .orElse(NONE);
    }

    public static Device of(String device) {
        return Arrays.stream(Device.values())
            .filter(d -> d.name().equals(device))
            .findFirst()
            .orElse(NONE);
    }
}
