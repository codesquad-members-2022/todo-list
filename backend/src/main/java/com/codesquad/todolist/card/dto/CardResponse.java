package com.codesquad.todolist.card.dto;

import com.codesquad.todolist.card.Card;
import java.time.LocalDateTime;

public class CardResponse {

    private final Integer cardId;
    private final String title;
    private final String content;
    private final String author;
    private final LocalDateTime createdDateTime;

    public CardResponse(Integer cardId, String title, String content, String author,
        LocalDateTime createdDateTime) {
        this.cardId = cardId;
        this.title = title;
        this.content = content;
        this.author = author;
        this.createdDateTime = createdDateTime;
    }

    public static CardResponse from(Card card) {
        return new CardResponse(
            card.getCardId(),
            card.getTitle(),
            card.getContent(),
            card.getAuthor(),
            card.getCreatedDateTime()
        );
    }

    public Integer getCardId() {
        return cardId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }
}
