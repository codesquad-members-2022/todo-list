package com.example.backend.controller.card.dto;

import com.example.backend.domain.card.Card;
import com.example.backend.domain.card.CardType;

import java.time.LocalDateTime;

public class CardDto {

    private Long id;
    private String writer;
    private String title;
    private Long position;
    private String content;
    private LocalDateTime createdAt;
    private CardType cardType;
    private Long memberId;

    public CardDto(Long id, String writer, Long position, String title, String content, LocalDateTime createdAt, CardType cardType, Long memberId) {
        this.writer = writer;
        this.id = id;
        this.position = position;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.cardType = cardType;
        this.memberId = memberId;
    }

    public CardDto() {
    }

    public CardDto(Card card) {
        this.id = card.getId();
        this.title = card.getTitle();
        this.content = card.getContent();
        this.createdAt = card.getCreatedAt();
        this.cardType = card.getCardType();
    }

    public Long getId() {
        return id;
    }

    public String getWriter() {
        return writer;
    }

    public String getTitle() {
        return title;
    }

    public Long getPosition() {
        return position;
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

    public Long getMemberId() {
        return memberId;
    }
}
