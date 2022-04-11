package com.team26.todolist.dto.request;

import com.team26.todolist.domain.Card;
import com.team26.todolist.domain.CardStatus;

public class CardMoveRequest {
    private Long id;
    private CardStatus newStatus;

    public Card toEntity() {
        return new Card(this.id, this.newStatus);
    }

    public Long getId() {
        return id;
    }

    public CardStatus getNewStatus() {
        return newStatus;
    }
}
