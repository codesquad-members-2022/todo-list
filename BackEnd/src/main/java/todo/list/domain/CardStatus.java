package todo.list.domain;

public enum CardStatus {
    TODO,
    IN_PROGRESS,
    DONE;

    public static CardStatus from(String stringCardStatus) {
        try {
            return valueOf(stringCardStatus);
        } catch (NullPointerException e) {
            return null;
        }
    }
}
