package com.team26.todolist.dto.response;

import com.team26.todolist.domain.Card;
import com.team26.todolist.domain.CardStatus;

public class CardResponse {
    private Long id;
    private String userId;
    private String title;
    private String contents;
    private CardStatus cardStatus;

    public CardResponse() {
    }

    public CardResponse(Long id, String userId, String title, String contents, CardStatus cardStatus) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.contents = contents;
        this.cardStatus = cardStatus;
    }

    public static CardResponse of(Card card) {
        return new CardResponse(
                card.getId(),
                card.getUserId(),
                card.getTitle(),
                card.getContents(),
                card.getCardStatus()
        );
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public CardStatus getCardStatus() {
        return cardStatus;
    }
}
