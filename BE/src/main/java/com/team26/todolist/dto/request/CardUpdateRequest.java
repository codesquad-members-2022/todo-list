package com.team26.todolist.dto.request;

import com.team26.todolist.domain.Card;

public class CardUpdateRequest {

    private Long id;
    private String title;
    private String contents;

    public Card toEntity() {
        return new Card(this.id, this.title, this.contents);
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }
}
