package com.team26.todolist.dto.request;

import com.team26.todolist.domain.Card;

public class CardRegistrationRequest {
    private String title;
    private String contents;
    private Long columnId;

    public Card toEntity() {
        return new Card(this.title, this.contents, this.columnId);
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public Long getColumnId() {
        return columnId;
    }
}
