package com.example.backend.controller.card;

import com.example.backend.domain.card.CardType;

import java.time.LocalDateTime;

public class TodoItem {
    private Long id;
    private String title;
    private String content;
    private CardType cardType;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;

    public TodoItem(Long id, String title, String content, CardType cardType, LocalDateTime createdAt, LocalDateTime lastModifiedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.cardType = cardType;
        this.createdAt = createdAt;
        this.lastModifiedAt = lastModifiedAt;
    }
}
