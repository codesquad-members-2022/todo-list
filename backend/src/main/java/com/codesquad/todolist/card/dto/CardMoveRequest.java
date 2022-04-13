package com.codesquad.todolist.card.dto;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;

public class CardMoveRequest {

    @ApiModelProperty(value = "목표 위치의 컬럼 Id", required = true)
    @NotNull(message = "columnId 값이 있어야 합니다")
    private Integer columnId;

    @ApiModelProperty(value = "목표 위치의 아래 카드 Id", required = true)
    @NotNull(message = "nextId 값이 있어야 합니다")
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
