package com.example.backend.domain.history;

import java.time.LocalDateTime;

public class History {
    private Long id;
    private String writer;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;
    private Action action;
    private Long memberId;
    private boolean visible;

    public History(String writer, String title, String content, Action action, Long memberId) {
        LocalDateTime now = LocalDateTime.now();
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.createdAt = now;
        this.lastModifiedAt = now;
        this.action = action;
        this.memberId = memberId;
        this.visible = true;
    }

    public History(Long id, String writer, String title, String content, LocalDateTime createdAt, LocalDateTime lastModifiedAt, String action, Long memberId, boolean visible) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.lastModifiedAt = lastModifiedAt;
        this.action = Action.valueOf(action);
        this.memberId = memberId;
        this.visible = visible;

    }
}
