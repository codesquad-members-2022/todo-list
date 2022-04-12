package com.codesquad.todolist.card.dto;

import javax.validation.constraints.NotNull;

public class CardMoveRequest {
    @NotNull(message = "목표 위치의 컬럼ID 값이 있어야 합니다")
    private Integer columnId;
    @NotNull(message = "목표 위치의 다음 카드ID 값이 있어야 합니다")
    private Integer nextCardId;

    private CardMoveRequest() {
    }

    public CardMoveRequest(Integer columnId, Integer nextCardId) {
        this.columnId = columnId;
        this.nextCardId = nextCardId;
    }

    public Integer getColumnId() {
        return columnId;
    }

    public Integer getNextCardId() {
        return nextCardId;
    }
}
