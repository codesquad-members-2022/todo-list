package todo.list.domain;

public enum Action {
    ADD("등록"),
    REMOVE("삭제"),
    UPDATE("변경"),
    MOVE("이동");

    public final String string;

    Action(String string) {
        this.string = string;
    }
}
