package com.example.backend.controller.history.dto;

import com.example.backend.domain.history.Action;
import com.example.backend.domain.history.History;

import java.time.LocalDateTime;

public class HistorySaveRequest {

    private String content;
    private String author;
    private Action action;
    private String font;
    private Long memberId;
    private Long cardId;
    private LocalDateTime createdAt;
    private boolean visible;

    public HistorySaveRequest() {
    }

    public HistorySaveRequest(String content, String author, Action action, String font, Long memberId, Long cardId) {
        this.content = content;
        this.author = author;
        this.action = action;
        this.font = font;
        this.memberId = memberId;
        this.cardId = cardId;
        this.createdAt = LocalDateTime.now();
        this.visible = true;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public Action getAction() {
        return action;
    }

    public String getFont() {
        return font;
    }

    public Long getMemberId() {
        return memberId;
    }

    public Long getCardId() {
        return cardId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public boolean isVisible() {
        return visible;
    }

    public History toEntity() {
        return new History(this.content, this.action, this.memberId, this.cardId);
    }
}
