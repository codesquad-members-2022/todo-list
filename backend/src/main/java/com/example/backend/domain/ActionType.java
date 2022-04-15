package com.example.backend.domain;

import java.util.HashMap;
import java.util.Map;

public enum ActionType {
    ADD("add", (title, prevColumnName, curColumnName) -> curColumnName + "에 " + title + "을(를) 등록하였습니다."),
    UPDATE("update", (title, prevColumnName, curColumnName) -> title + "이(가) 수정되었습니다."),
    REMOVE("remove", (title, prevColumnName, curColumnName) -> curColumnName + "의 " + title + "이(가) 삭제되었습니다."),
    MOVE("move", (title, prevColumnName, curColumnName) -> title + "을(를) " + prevColumnName + "에서 " + curColumnName + "(으)로 이동하였습니다.");

    private static final Map<String, String> displayName = new HashMap<>();

    static {
        displayName.put("to_do", "해야할 일");
        displayName.put("in_progress", "하고 있는 일");
        displayName.put("done", "완료한 일");
    }

    private final String name;
    private final TextCreator<String, String, String, String> expression;

    ActionType(String name, TextCreator<String, String, String, String> expression) {
        this.name = name;
        this.expression = expression;
    }

    public String getName() {
        return name;
    }

    public String getText(String title, String prevColumnName, String curColumnName) {
        return expression.create(title, displayName.get(prevColumnName), displayName.get(curColumnName));
    }
}
