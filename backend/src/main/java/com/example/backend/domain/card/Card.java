package com.example.backend.domain.card;

import java.time.LocalDateTime;

public class Card {
    private Long id;
    private String title;
    private String content;
    private CardType cardType;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;
    private boolean visible;
    private Long columnId;

    public Card(Long id, String title, String content, CardType cardType) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.cardType = cardType;
    }

    public Card(String title, String content, CardType cardType) {
        this.title = title;
        this.content = content;
        this.cardType = cardType;
    }

    public Card(long id, String title, String content, String cardType, LocalDateTime createdAt, LocalDateTime lastModifiedAt, boolean visible, long columnId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.cardType = CardType.valueOf(cardType);
        this.createdAt = createdAt;
        this.lastModifiedAt = lastModifiedAt;
        this.visible = true;
        this.columnId = columnId;
    }

    public LocalDateTime getLastModifiedAt() {
        return lastModifiedAt;
    }

    public boolean isVisible() {
        return visible;
    }

    public Long getColumnId() {
        return columnId;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public CardType getCardType() {
        return cardType;
    }
}

