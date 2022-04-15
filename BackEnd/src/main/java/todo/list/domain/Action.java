package todo.list.domain;

public enum Action {
    ADD,
    REMOVE,
    UPDATE,
    MOVE;

    public boolean isMove() {
        return this == MOVE;
    }
}
