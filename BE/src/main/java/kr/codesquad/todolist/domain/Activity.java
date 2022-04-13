package kr.codesquad.todolist.domain;

public enum Activity {
    ADD("add"),
    REMOVE("remove"),
    UPDATE("update"),
    MOVE("move");

    private final String activity;

    Activity(String activity) {
        this.activity = activity;
    }

    public String getActivity() {
        return activity;
    }
}
