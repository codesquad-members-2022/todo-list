package com.example.backend.domain.card;

import java.time.LocalDateTime;

public class Card {
    private Long id;
    private String title;
    private String content;
    private String writer;
    private CardType cardType;
    private LocalDateTime createdAt;
    private Long position;
    private LocalDateTime lastModifiedAt;
    private boolean visible;
    private Long memberId;

    public Card(Long id, String writer, String title, String content, CardType cardType) {
        this.id = id;
        this.writer = writer;
        this.position = 0L;
        this.title = title;
        this.content = content;
        this.cardType = cardType;
    }

    public Card(String writer, String title, String content, CardType cardType, Long memberId) {
        this.writer = writer;
        this.position = 0L;
        this.title = title;
        this.content = content;
        this.cardType = cardType;
        this.memberId = memberId;
    }

    public Card(long id, String writer, String title, String content, String cardType, LocalDateTime createdAt, LocalDateTime lastModifiedAt, Long memberId) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.cardType = CardType.valueOf(cardType);
        this.createdAt = createdAt;
        this.lastModifiedAt = lastModifiedAt;
        this.visible = true;
        this.memberId = memberId;
    }

    public Long getPosition() {
        return position;
    }

    public void setPosition(Long position) {
        this.position = position;
    }

    public LocalDateTime getLastModifiedAt() {
        return lastModifiedAt;
    }

    public boolean isVisible() {
        return visible;
    }

    public Long getMemberId() {
        return memberId;
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

    public String getWriter() {
        return writer;
    }
}

