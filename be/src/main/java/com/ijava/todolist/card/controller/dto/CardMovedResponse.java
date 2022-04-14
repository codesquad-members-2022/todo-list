package com.ijava.todolist.card.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CardMovedResponse {
    private Long cardId;
    private Long oldColumn;
    private Long newColumn;

    public CardMovedResponse(Long cardId, Long oldColumn, Long newColumn) {
        this.cardId = cardId;
        this.oldColumn = oldColumn;
        this.newColumn = newColumn;
    }
}
