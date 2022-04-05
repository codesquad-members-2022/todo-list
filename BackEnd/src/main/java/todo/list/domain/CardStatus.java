package todo.list.domain;

public enum CardStatus {
    TODO("해야할 일"),
    IN_PROGRESS("하고 있는 일"),
    DONE("완료한 일");

    public final String string;

    CardStatus(String string) {
        this.string = string;
    }
}
