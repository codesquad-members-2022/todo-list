package com.codesquad.todolist.card.dto;

import javax.validation.constraints.NotNull;

import com.codesquad.todolist.card.Card;

public class CardCreateRequest {

    @NotNull(message = "column Id 값이 있어야 합니다.")
    private Integer columnId;
    @NotNull(message = "title 값이 있어야 합니다.")
    private String title;
    @NotNull(message = "content 값이 있어야 합니다.")
    private String content;
    @NotNull(message = "author 값이 있어야 합니다.")
    private String author;
    private Integer nextId;

    private CardCreateRequest() {
    }

    public CardCreateRequest(Integer columnId, String title, String author, String content, Integer nextId) {
        this.columnId = columnId;
        this.title = title;
        this.content = content;
        this.author = author;
        this.nextId = nextId;
    }

    public Card toEntity() {
        return new Card(columnId, title, content, author, nextId);
    }

    public Integer getColumnId() {
        return columnId;
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

    @Override
    public String toString() {
        return "CardCreateRequest{" +
            "columnId=" + columnId +
            ", title='" + title + '\'' +
            ", content='" + content + '\'' +
            ", author='" + author + '\'' +
            '}';
    }
}
