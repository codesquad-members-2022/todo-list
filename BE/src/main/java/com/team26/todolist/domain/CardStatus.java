package com.team26.todolist.domain;

import java.util.Arrays;

public enum CardStatus {
    TODO("해야할 일"),
    ONGOING("하고 있는 일"),
    DONE("완료한 일"),
    UNCLASSIFIED("미분류");

    private String name;

    CardStatus(String name) {
        this.name = name;
    }

    //TODO
    // TODO, ONGOING으로 들어오는 요청들 매핑할 수 있도록 변경예정
    public static CardStatus createCardStatus(String cardStatus) {
        return Arrays.stream(CardStatus.values())
                .filter(status -> status.getName().equals(cardStatus))
                .findAny()
                .orElse(CardStatus.UNCLASSIFIED);
    }

    public String getName() {
        return name;
    }
}
