package todo.list.domain;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Action {
    ADD,
    REMOVE,
    UPDATE,
    MOVE;

    @JsonCreator
    public static Action valueOfWithCaseInsensitive(String name) {
        name = name.toUpperCase();
        return valueOf(name);
    }
}
