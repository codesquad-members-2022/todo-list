package todo.list.domain;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum CardStatus {
    TODO,
    IN_PROGRESS,
    DONE;

    public static CardStatus from(String name) {
        try {
            return valueOf(name);
        } catch (NullPointerException e) {
            return null;
        }
    }
}
