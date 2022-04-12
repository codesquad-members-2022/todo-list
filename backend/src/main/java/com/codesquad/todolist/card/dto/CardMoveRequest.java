package com.codesquad.todolist.card.dto;

import javax.validation.constraints.NotNull;

public class CardMoveRequest {
    @NotNull(message = "목표 위치의 컬럼ID 값이 있어야 합니다")
    private Integer columnId;
    private Integer nextId;

    private CardMoveRequest() {
    }

    public CardMoveRequest(Integer columnId, Integer nextId) {
        this.columnId = columnId;
        this.nextId = nextId;
    }

    public Integer getColumnId() {
        return columnId;
    }

    public Integer getNextId() {
        return nextId;
    }

    @Override
    public String toString() {
        return "CardMoveRequest{" +
            "columnId=" + columnId +
            ", nextId=" + nextId +
            '}';
    }
}
