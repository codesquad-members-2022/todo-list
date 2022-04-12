package com.codesquad.todolist.card.dto;

public class CardMoveRequest {

    private Integer columnId;
    private Integer order;

    private CardMoveRequest() {
    }

    public CardMoveRequest(Integer columnId, Integer order) {
        this.columnId = columnId;
        this.order = order;
    }

    public Integer getColumnId() {
        return columnId;
    }

    public Integer getOrder() {
        return order;
    }
}
