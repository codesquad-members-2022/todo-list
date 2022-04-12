package com.example.backend.controller.card.dto;

import com.example.backend.domain.card.Card;
import com.example.backend.domain.card.CardType;

import java.time.LocalDateTime;

public class CardDto {

    private Long id;
    private String title;
    private String writer;
    private Long position;
    private String content;
    private LocalDateTime createdAt;
    private CardType cardType;
    private Long memberId;

    public CardDto(Long id, String title, String writer, Long position, String content, LocalDateTime createdAt, CardType cardType, Long memberId) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.position = position;
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
        this.writer = card.getWriter();
        this.position = card.getPosition();
        this.content = card.getContent();
        this.createdAt = card.getCreatedAt();
        this.cardType = card.getCardType();
        this.memberId = card.getMemberId();
    }

    public Long getId() {
        return id;
    }

    public CardType getCardType() {
        return cardType;
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

    public String getWriter() {
        return writer;
    }

    public Long getPosition() {
        return position;
    }

    public Long getMemberId() {
        return memberId;
    }

    public Card updateCard(Long id) {
        return new Card(id, title, content);
    }

    public Card writeCard() {
        return new Card(title, writer, content, position, memberId, cardType);
    }
}
