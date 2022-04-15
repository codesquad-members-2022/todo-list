package com.todolist.domain.dto;

import com.todolist.domain.Card;

import lombok.Getter;

@Getter
public class CardInformationDto {

    private final Integer cardId;
    private final String cardTitle;
    private final String cardContent;
    private final String boardName;

    private CardInformationDto(Integer cardId, String cardTitle, String cardContent, String boardName) {
        this.cardId = cardId;
        this.cardTitle = cardTitle;
        this.cardContent = cardContent;
        this.boardName = boardName;
    }

    public static CardInformationDto of(Card card) {
        return new CardInformationDto(card.getCardId(), card.getCardTitle(), card.getCardContent(), card.getBoardName());
    }
}
