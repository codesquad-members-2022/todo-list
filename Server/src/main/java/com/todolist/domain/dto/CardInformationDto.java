package com.todolist.domain.dto;

import lombok.Getter;

@Getter
public class CardInformationDto {

    private final Integer cardId;
    private final String cardTitle;
    private final String cardContent;
    private final String boardName;

    public CardInformationDto(Integer cardId, String cardTitle, String cardContent, String boardName) {
        this.cardId = cardId;
        this.cardTitle = cardTitle;
        this.cardContent = cardContent;
        this.boardName = boardName;
    }
}
