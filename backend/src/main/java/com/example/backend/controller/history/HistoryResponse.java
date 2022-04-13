package com.example.backend.controller.history;

import com.example.backend.domain.history.Action;

import java.time.LocalDateTime;

public class HistoryResponse {
    private String writer;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;
    private Action action;

    public HistoryResponse(String writer, String title, String content, LocalDateTime createdAt, LocalDateTime lastModifiedAt, Action action) {
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.lastModifiedAt = lastModifiedAt;
        this.action = action;
    }

    public HistoryResponse() {
    }

    public String getWriter() {
        return writer;
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

    public LocalDateTime getLastModifiedAt() {
        return lastModifiedAt;
    }

    public Action getAction() {
        return action;
    }
}
