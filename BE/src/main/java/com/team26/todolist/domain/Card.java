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

    public Card(Long id, String title, String contents, String userId, CardStatus cardStatus, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.userId = userId;
        this.cardStatus = cardStatus;
        this.isDeleted = false;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public String getUserId() {
        return userId;
    }

    public CardStatus getCardStatus() {
        return cardStatus;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
