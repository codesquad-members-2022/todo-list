package todo.list.domain;

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
