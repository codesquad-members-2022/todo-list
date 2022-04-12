package com.example.backend.controller.history;

import com.example.backend.domain.card.CardType;
import com.example.backend.domain.history.Action;

import java.time.LocalDateTime;

public class HistoryResponse {
    private Long id;
    private String author;
    private String content;
    private LocalDateTime createdAt;
    private CardType cardType;
    private Action action;

    public HistoryResponse(Long id, String content, LocalDateTime createdAt, String action, String author, String cardType) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.createdAt = createdAt;
        this.action = Action.valueOf(action);
        this.cardType = CardType.valueOf(cardType);
    }

    public HistoryResponse() {
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Action getAction() {
        return action;
    }
}
