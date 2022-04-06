package com.team26.todolist.domain;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class Card {
    private Long id;
    @NonNull
    private String title;
    @NonNull
    private String contents;
    @NonNull
    private String userId;
    @NonNull
    private CardStatus cardStatus;
    private boolean isDeleted;
    @NonNull
    private LocalDateTime createdAt;

    public Card(@NonNull String title, @NonNull String contents, @NonNull String userId, @NonNull CardStatus cardStatus) {
        this.title = title;
        this.contents = contents;
        this.userId = userId;
        this.cardStatus = cardStatus;
        this.isDeleted = false;
        this.createdAt = LocalDateTime.now();
    }
}
