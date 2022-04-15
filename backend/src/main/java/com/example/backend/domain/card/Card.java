package com.example.backend.domain.card;

import java.beans.Transient;
import java.time.LocalDateTime;

public class Card {
    private Long id;
    private String writer;
    private Long position;
    private String title;
    private String content;
    private CardType cardType;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;
    private boolean visible;
    private Long memberId;

    public Card(String title, String writer, String content, Long position, Long memberId, CardType cardType) {
        this.title = title;
        this.writer = writer;
        this.content = content;
        this.position = position;
        this.memberId = memberId;
        this.cardType = cardType;
        this.visible = true;
    }

    public Card(Long id, String writer, Long position, String title, String content, CardType cardType, LocalDateTime createdAt, LocalDateTime lastModifiedAt, boolean visible, long memberId) {
        this.id = id;
        this.writer = writer;
        this.position = position;
        this.title = title;
        this.content = content;
        this.cardType = cardType;
        this.createdAt = createdAt;
        this.lastModifiedAt = lastModifiedAt;
        this.visible = true;
        this.memberId = memberId;
    }

    public LocalDateTime getLastModifiedAt() {
        return lastModifiedAt;
    }

    public boolean isVisible() {
        return visible;
    }

    public Long getId() {
        return id;
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

    public CardType getCardType() {
        return cardType;
    }

    @Transient
    public String getCardTypeName(){
        return cardType.name();
    }

    public String getWriter() {
        return writer;
    }

    public Long getPosition() {
        return position;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void updateCard(String title, String content, CardType cardType, Long maxPositionNumber) {
        if (cardType != null) {
            this.cardType = cardType;
        }
        if (maxPositionNumber != null) {
            this.position = maxPositionNumber + 1;
        }
        this.title = title;
        this.content = content;
        this.lastModifiedAt = LocalDateTime.now();
    }

    public void changePosition(Long newPosition) {
        this.position = newPosition;
    }

    public void changeCardType(CardType newCardType) {
        this.cardType = newCardType;
    }
}

