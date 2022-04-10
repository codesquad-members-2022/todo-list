package com.example.backend.controller.card.dto;

import com.example.backend.domain.card.CardType;

import java.time.LocalDateTime;

public class Task {
    private Long id;
    private String title;
    private String content;
    private CardType cardType;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;

    public Task(Long id, String title, String content, String cardType, LocalDateTime createdAt, LocalDateTime lastModifiedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.cardType = CardType.valueOf(cardType);
        this.createdAt = createdAt;
        this.lastModifiedAt = lastModifiedAt;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public CardType getCardType() {
        return cardType;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getLastModifiedAt() {
        return lastModifiedAt;
    }

}
