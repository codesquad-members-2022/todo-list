package com.ijava.todolist.card.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CardDeleteResponse {
    private Long cardId;

    public CardDeleteResponse(Long cardId) {
        this.cardId = cardId;
    }
}
