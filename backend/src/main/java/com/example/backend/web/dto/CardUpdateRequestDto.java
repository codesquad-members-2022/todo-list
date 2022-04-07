package com.example.backend.web.dto;

import io.swagger.annotations.ApiModelProperty;

public class CardUpdateRequestDto {
    @ApiModelProperty(example = "수정할 카드 제목")
    private String title;
    @ApiModelProperty(example = "수정할 카드 내용")
    private String content;

    public CardUpdateRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
