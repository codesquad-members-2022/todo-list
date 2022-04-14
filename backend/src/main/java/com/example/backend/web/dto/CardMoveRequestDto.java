package com.example.backend.web.dto;

import io.swagger.annotations.ApiModelProperty;

public class CardMoveRequestDto {
    @ApiModelProperty(example = "움직일 카드의 id")
    private Long id;
    @ApiModelProperty(example = "카드가 이동할 행 위치")
    private Long newIndex;
    @ApiModelProperty(example = "카드가 이동할 column")
    private String newColumnName;

    public CardMoveRequestDto(Long id, Long newIndex, String newColumnName) {
        this.id = id;
        this.newIndex = newIndex;
        this.newColumnName = newColumnName;
    }

    public Long getId() {
        return id;
    }

    public Long getNewIndex() {
        return newIndex;
    }

    public String getNewColumnName() {
        return newColumnName;
    }
}
