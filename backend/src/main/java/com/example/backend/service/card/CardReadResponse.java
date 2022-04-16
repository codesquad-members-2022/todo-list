package com.example.backend.service.card;

import com.example.backend.domain.card.Card;
import com.example.backend.domain.card.CardType;

import java.beans.Transient;
import java.time.LocalDateTime;

public class CardReadResponse {
    private final Long id;
    private final String title;
    private final String writer;
    private final String content;
    private final LocalDateTime createdAt;
    private final CardType cardType;

    public CardReadResponse(Card card) {
        this.id = card.getId();
        this.title = card.getTitle();
        this.writer = card.getWriter();
        this.content = card.getContent();
        this.createdAt = card.getCreatedAt();
        this.cardType = card.getCardType();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getWriter() {
        return writer;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public CardType getCardType() {
        return cardType;
    }

    @Transient
    public String getCardTypeName(){
        return cardType.getName();
    }
}
