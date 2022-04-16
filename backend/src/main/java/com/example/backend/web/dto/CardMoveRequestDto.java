package com.example.backend.web.dto;

import com.example.backend.domain.Card;
import io.swagger.annotations.ApiModelProperty;

public class CardMoveRequestDto {
    @ApiModelProperty(example = "움직일 카드의 id")
    private Long id;
    @ApiModelProperty(example = "카드가 이동할 행 위치")
    private Long newOrderIndex;
    @ApiModelProperty(example = "카드가 이동할 column")
    private String newColumnName;

    public CardMoveRequestDto() {
    }

    public CardMoveRequestDto(Long id, Long newOrderIndex, String newColumnName) {
        this.id = id;
        this.newOrderIndex = newOrderIndex;
        this.newColumnName = newColumnName;
    }

    public Long getId() {
        return id;
    }

    public Long getNewOrderIndex() {
        return newOrderIndex;
    }

    public String getNewColumnName() {
        return newColumnName;
    }

    public Card toEntity() {
        return new Card.Builder()
                .id(id)
                .columnName(newColumnName)
                .orderIndex(newOrderIndex)
                .build();
    }
}
