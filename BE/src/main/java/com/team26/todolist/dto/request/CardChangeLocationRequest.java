package com.team26.todolist.dto.request;
import com.team26.todolist.domain.Card;

public class CardChangeLocationRequest {
    private Long id;
    private Long newColumnId;
    private Long upperCardId;
    private Long lowerCardId;

    public Card toEntity() {
        return new Card(this.id, this.newColumnId);
    }

    public Long getId() {
        return id;
    }

    public Long getNewColumnId() {
        return newColumnId;
    }

    public Long getUpperCardId() {
        return upperCardId;
    }

    public Long getLowerCardId() {
        return lowerCardId;
    }
}
