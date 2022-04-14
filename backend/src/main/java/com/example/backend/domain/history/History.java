package com.example.backend.domain.history;

import java.time.LocalDateTime;

public class History {

    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;
    private Action action;
    private Long memberId;
    private Long cardId;
    private String font;
    private boolean visible;

    public History(String content, Action action, Long memberId, Long cardId) {
        LocalDateTime now = LocalDateTime.now();
        this.content = content;
        this.createdAt = now;
        this.lastModifiedAt = now;
        this.action = action;
        this.memberId = memberId;
        this.cardId = cardId;
        this.font = "Helvetica";
        this.visible = true;
    }

    public History(Long id, String content, LocalDateTime created_at, LocalDateTime last_modified_at, String action, Long memberId, Long cardId, boolean visible) {
        this.id = id;
        this.content = content;
        this.createdAt = created_at;
        this.lastModifiedAt = last_modified_at;
        this.action = Action.valueOf(action);
        this.memberId = memberId;
        this.cardId = cardId;
        this.font = "Helvetica";
        this.visible = visible;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getLastModifiedAt() {
        return lastModifiedAt;
    }

    public Action getAction() {
        return action;
    }

    public Long getMemberId() {
        return memberId;
    }

    public Long getCardId() {
        return cardId;
    }

    public boolean isVisible() {
        return visible;
    }

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }
}
