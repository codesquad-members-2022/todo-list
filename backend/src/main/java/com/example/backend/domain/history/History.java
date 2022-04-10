package com.example.backend.domain.history;

import java.time.LocalDateTime;

public class History {
    private Long id;
    private String writer;
    private String content;
    private String font;
    private LocalDateTime createdAt;
    private Action action;
    private Long memberId;
    private boolean visible;
}
