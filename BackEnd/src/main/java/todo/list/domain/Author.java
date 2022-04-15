package todo.list.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Author {
    ANDROID("Android");
    @JsonValue
    private String stringValue;

    Author(String stringValue) {
        this.stringValue = stringValue;
    }

    @JsonCreator
    public static Author valueOfWithCaseInsensitive(String name) {
        name = name.toUpperCase();
        return valueOf(name);
    }
}
