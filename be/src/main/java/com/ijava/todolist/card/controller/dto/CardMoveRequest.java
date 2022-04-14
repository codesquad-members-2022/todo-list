package com.ijava.todolist.card.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CardMoveRequest {
    private Long cardId;
    private Long columnId;

    public CardMoveRequest(Long cardId, Long columnId) {
        this.cardId = cardId;
        this.columnId = columnId;
    }
}
