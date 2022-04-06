package todo.list.domain;

public enum Action {
    ADD,
    REMOVE,
    UPDATE,
    MOVE;

    public static Action from(String string) {
        string = string.toUpperCase();
        if (ADD.toString().equals(string)) {
            return ADD;
        }
        if (REMOVE.toString().equals(string)) {
            return REMOVE;
        }
        if (UPDATE.toString().equals(string)) {
            return UPDATE;
        }
        if (MOVE.toString().equals(string)) {
            return MOVE;
        }
        return null;
    }

}
