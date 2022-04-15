package com.todolist.domain.dto;

import lombok.Getter;

@Getter
public class CardResponseDto {
    private final Integer cardId;

    public CardResponseDto(Integer cardId) {
        this.cardId = cardId;
    }
}
