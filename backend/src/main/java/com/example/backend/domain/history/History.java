package com.example.backend.domain.history;

import java.time.LocalDateTime;

public class History {
    private Long id;
    private String writer;
    private String title;
    private String content;
    private String font;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;
    private Action action;
    private Long memberId;
    private boolean visible;

    public History(String writer, String title, String content, String font, Action action, Long memberId) {
        LocalDateTime now = LocalDateTime.now();
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.font = font;
        this.createdAt = now;
        this.lastModifiedAt = now;
        this.action = action;
        this.memberId = memberId;
        this.visible = true;
    }
}
