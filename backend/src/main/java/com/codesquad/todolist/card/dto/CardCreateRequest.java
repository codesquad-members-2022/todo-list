package com.codesquad.todolist.card.dto;

import javax.validation.constraints.NotNull;

import com.codesquad.todolist.card.Card;

public class CardCreateRequest {

    @NotNull(message = "cloumn must not be null")
    private Integer columnId;
    @NotNull(message = "title must not be null")
    private String title;
    @NotNull(message = "content must not be null")
    private String content;
    @NotNull(message = "author must not be null")
    private String author;

    private CardCreateRequest() {
    }

    public CardCreateRequest(Integer columnId, String title, String author, String content) {
        this.columnId = columnId;
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Card toEntity(int order) {
        return new Card(columnId, title, content, author, order);
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
