package com.example.backend.web.dto;

import io.swagger.annotations.ApiModelProperty;

public class CardMoveRequestDto {
    @ApiModelProperty(example = "움직일 카드의 id")
    private Long id;
    @ApiModelProperty(example = "현재 카드의 행 위치")
    private Long currentIndex;
    @ApiModelProperty(example = "현재 카드의 column")
    private String currentStatus;
    @ApiModelProperty(example = "카드가 이동할 행 위치")
    private Long newIndex;
    @ApiModelProperty(example = "카드가 이동할 column")
    private String newStatus;

    public CardMoveRequestDto(Long id, Long currentIndex, String currentStatus, Long newIndex, String newStatus) {
        this.id = id;
        this.currentIndex = currentIndex;
        this.currentStatus = currentStatus;
        this.newIndex = newIndex;
        this.newStatus = newStatus;
    }

    public Long getId() {
        return id;
    }

    public Long getCurrentIndex() {
        return currentIndex;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public Long getNewIndex() {
        return newIndex;
    }

    public String getNewStatus() {
        return newStatus;
    }
}
