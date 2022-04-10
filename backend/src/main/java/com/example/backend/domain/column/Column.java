package com.example.backend.domain.column;

import java.time.LocalDateTime;

public class Column {
    private Long id;
    private String writer;
    private LocalDateTime createdAt;
    private Long memberId;
    private boolean visible;
}
