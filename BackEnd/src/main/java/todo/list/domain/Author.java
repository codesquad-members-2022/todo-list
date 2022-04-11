package todo.list.domain;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Author {
    ANDROID;

    @JsonCreator
    public static Author valueOfWithCaseInsensitive(String name) {
        name = name.toUpperCase();
        return valueOf(name);
    }
}
