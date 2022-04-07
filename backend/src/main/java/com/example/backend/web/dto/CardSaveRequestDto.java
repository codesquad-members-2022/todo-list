package com.example.backend.web.dto;

import com.example.backend.domain.Card;
import io.swagger.annotations.ApiModelProperty;

public class CardSaveRequestDto {
    @ApiModelProperty(example = "카드 제목")
    private String title;
    @ApiModelProperty(example = "카드 내용")
    private String content;
    @ApiModelProperty(example = "작성된 시스템")
    private String authorSystem;
    @ApiModelProperty(example = "작성된 column명")
    private String columnName;

    public CardSaveRequestDto(String title, String content, String authorSystem, String columnName) {
        this.title = title;
        this.content = content;
        this.authorSystem = authorSystem;
        this.columnName = columnName;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAuthorSystem() {
        return authorSystem;
    }

    public String getColumnName() {
        return columnName;
    }

    public Card toEntity() {
        return new Card.Builder()
                .title(title)
                .content(content)
                .authorSystem(authorSystem)
                .columnName(columnName)
                .build();
    }
}
