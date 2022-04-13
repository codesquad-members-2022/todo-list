package com.team26.todolist.dto.request;
import com.team26.todolist.domain.Card;

public class CardMoveRequest {
    private Long id;
    private Long newColumnId;

    public Card toEntity() {
        return new Card(this.id, this.newColumnId);
    }

    public Long getId() {
        return id;
    }

    public Long getNewColumnId() {
        return newColumnId;
    }
}
