package com.codesquad.todolist.card.dto;

import javax.validation.constraints.NotNull;

import com.codesquad.todolist.card.Card;

import io.swagger.annotations.ApiModelProperty;

public class CardCreateRequest {

    @ApiModelProperty(value = "목표 컬럼 Id", required = true)
    @NotNull(message = "columnId 값이 있어야 합니다.")
    private Integer columnId;

    @ApiModelProperty(value = "카드의 제목", required = true)
    @NotNull(message = "title 값이 있어야 합니다.")
    private String title;

    @ApiModelProperty(value = "카드의 내용", required = true)
    @NotNull(message = "content 값이 있어야 합니다.")
    private String content;

    @ApiModelProperty(value = "작성자", required = true)
    @NotNull(message = "author 값이 있어야 합니다.")
    private String author;

    private CardCreateRequest() {
    }

    public CardCreateRequest(Integer columnId, String title, String author, String content) {
        this.columnId = columnId;
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Card toEntity(Integer nextId) {
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
