package com.codesquad.todolist.card.dto;

import javax.validation.constraints.NotNull;

public class CardUpdateRequest {
    @NotNull(message = "title must not be null")
    private String title;
    @NotNull(message = "content must not be null")
    private String content;
    @NotNull(message = "author must not be null")
    private String author;

    private CardUpdateRequest() {
    }

    public CardUpdateRequest(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
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
        return "CardUpdateRequest{" +
            "title='" + title + '\'' +
            ", content='" + content + '\'' +
            ", author='" + author + '\'' +
            '}';
    }
}
