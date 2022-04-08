package com.example.backend.web.dto;

import io.swagger.annotations.ApiModelProperty;

public class CardListResponseDto {
    @ApiModelProperty(example = "카드 제목")
    private String title;
    @ApiModelProperty(example = "카드 내용")
    private String content;
    @ApiModelProperty(example = "작성된 시스템")
    private String authorSystem;

    public CardListResponseDto(String title, String content, String authorSystem) {
        this.title = title;
        this.content = content;
        this.authorSystem = authorSystem;
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
}
