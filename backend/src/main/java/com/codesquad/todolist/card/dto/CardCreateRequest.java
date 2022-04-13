package com.codesquad.todolist.card.dto;

import com.codesquad.todolist.card.Card;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;

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

    @ApiModelProperty(value = "목표 컬럼의 최상위 카드 Id", required = true)
    @NotNull(message = "nextId 값이 있어야 합니다.")
    private Integer nextId;

    private CardCreateRequest() {
    }

    public CardCreateRequest(Integer columnId, String title, String author, String content,
        Integer nextId) {
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

    public Integer getNextId() {
        return nextId;
    }

    @Override
    public String toString() {
        return "CardCreateRequest{" +
            "columnId=" + columnId +
            ", title='" + title + '\'' +
            ", content='" + content + '\'' +
            ", author='" + author + '\'' +
            ", nextId=" + nextId +
            '}';
    }
}
