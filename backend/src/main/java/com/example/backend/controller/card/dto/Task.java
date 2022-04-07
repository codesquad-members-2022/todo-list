package com.example.backend.controller.card.dto;

import java.time.LocalDateTime;

public class Task {
    private Long id;
    private String title;
    private String content;
    private String cardType;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;

    public Task(Long id, String title, String content, String cardType, LocalDateTime createdAt, LocalDateTime lastModifiedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.cardType = cardType;
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

    public String getCardType() {
        return cardType;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getLastModifiedAt() {
        return lastModifiedAt;
    }
}
