package com.example.backend.controller.card.dto;

import com.example.backend.domain.card.Card;
import com.example.backend.domain.card.CardType;

import java.time.LocalDateTime;

public class CardSaveResponse {
    private final Long id;
    private final String title;
    private final String writer;
    private final String content;
    private final LocalDateTime createdAt;
    private final Long position;
    private final CardType cardType;

    public CardSaveResponse(Card card) {
        this.id = card.getId();
        this.title = card.getTitle();
        this.writer = card.getWriter();
        this.content = card.getContent();
        this.createdAt = card.getCreatedAt();
        this.cardType = card.getCardType();
        this.position = card.getPosition();
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

    public Long getPosition() {
        return position;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public CardType getCardType() {
        return cardType;
    }

}
