package com.team26.todolist.dto.request;

import com.team26.todolist.domain.Card;
import com.team26.todolist.domain.CardStatus;

public class CardRegistrationRequest {
    private String title;
    private String contents;
    private CardStatus cardStatus;

    public Card toEntity() {
        return new Card(this.title, this.contents, this.cardStatus);
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
