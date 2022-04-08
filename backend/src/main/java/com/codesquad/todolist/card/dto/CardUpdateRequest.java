package com.codesquad.todolist.card.dto;

import com.codesquad.todolist.card.Card;

public class CardUpdateRequest {

    private String title;
    private String content;
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
