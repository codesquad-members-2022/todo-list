package com.todolist.domain.dto;

import com.todolist.domain.Card;

import lombok.Getter;

@Getter
public class CardMoveDto {

    private final Integer cardId;
    private final String movedBoardName;
    private final Integer nextCardId;

    public CardMoveDto(Integer cardId, String movedBoardName, Integer nextCardId) {
        this.cardId = cardId;
        this.movedBoardName = movedBoardName;
        this.nextCardId = nextCardId;
    }

    public Card toCard(Long boardIdx) {
        return Card.builder()
            .cardId(cardId)
            .boardName(movedBoardName)
            .boardIdx(boardIdx)
            .build();
    }
}
