package com.codesquad.todolist.card.dto;

import io.swagger.annotations.ApiModelProperty;

public class CardUpdateRequest {

    @ApiModelProperty(value = "카드의 제목")
    private String title;

    @ApiModelProperty(value = "카드의 내용")
    private String content;

    @ApiModelProperty(value = "작성자")
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
