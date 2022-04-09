package com.example.backend.controller.card.dto;

import com.example.backend.domain.card.Card;
import com.example.backend.domain.card.CardType;

import java.time.LocalDateTime;

public class CardDto {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private CardType cardType;

    public CardDto(Long id, String title, String content, LocalDateTime createdAt, CardType cardType) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.cardType = cardType;
    }

    public CardDto() {
    }

    public CardDto(Card card) {
        this.id = card.getId();
        this.title = card.getTitle();
        this.content = card.getContent();
        this.createdAt = card.getCreatedAt();
        this.cardType = card.getCardType();
    }

    public Long getId() {
        return id;
    }

    public CardType getCardType() {
        return cardType;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public CardDto of(Card card) {
        return new CardDto(card.getId(), card.getTitle(), card.getContent(), card.getCreatedAt(), card.getCardType());
    }
}
