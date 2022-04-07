package com.example.backend.web.dto;

import io.swagger.annotations.ApiModelProperty;

public class CardMoveRequestDto {
    @ApiModelProperty(example = "움직일 카드의 id")
    private Long id;
    @ApiModelProperty(example = "현재 카드의 행 위치")
    private Long currentIndex;
    @ApiModelProperty(example = "현재 카드의 column")
    private String currentColumnName;
    @ApiModelProperty(example = "카드가 이동할 행 위치")
    private Long newIndex;
    @ApiModelProperty(example = "카드가 이동할 column")
    private String newColumnName;

    public CardMoveRequestDto(Long id, Long currentIndex, String currentColumnName, Long newIndex, String newColumnName) {
        this.id = id;
        this.currentIndex = currentIndex;
        this.currentColumnName = currentColumnName;
        this.newIndex = newIndex;
        this.newColumnName = newColumnName;
    }

    public Long getId() {
        return id;
    }

    public Long getCurrentIndex() {
        return currentIndex;
    }

    public String getCurrentColumnName() {
        return currentColumnName;
    }

    public Long getNewIndex() {
        return newIndex;
    }

    public String getNewColumnName() {
        return newColumnName;
    }
}
