package com.example.backend.service.card;

import com.example.backend.domain.card.CardType;

public class CardPositionChangeRequest {

    private CardType cardType;
    private Long newPosition;

    public CardPositionChangeRequest() {
    }

    public CardPositionChangeRequest(CardType cardType, Long newPosition) {
        this.cardType = cardType;
        this.newPosition = newPosition;
    }

    public CardType getCardType() {
        return cardType;
    }

    public Long getNewPosition() {
        return newPosition;
    }
}
