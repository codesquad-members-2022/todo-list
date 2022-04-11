package com.codesquad.todolist.card.dto;

import javax.validation.constraints.NotNull;

public class CardUpdateRequest {

    @NotNull(message = "title 값이 있어야 합니다.")
    private String title;
    @NotNull(message = "content 값이 있어야 합니다.")
    private String content;
    @NotNull(message = "author 값이 있어야 합니다.")
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
