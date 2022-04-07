package com.team26.todolist.domain;

import java.time.LocalDateTime;

public class Card {
    private Long id;
    private String title;
    private String contents;
    private String userId;
    private CardStatus cardStatus;
    private boolean isDeleted;
    private LocalDateTime createdAt;

    public Card(String title, String contents, String userId, CardStatus cardStatus) {
        this.title = title;
        this.contents = contents;
        this.userId = userId;
        this.cardStatus = cardStatus;
        this.isDeleted = false;
        this.createdAt = LocalDateTime.now();
    }
}
