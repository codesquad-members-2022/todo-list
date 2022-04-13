package com.example.backend.web.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

public class Column {
    @ApiModelProperty(example = "column 이름")
    private final String columnType;
    private final List<CardListResponseDto> cards;

    public Column(String columnType) {
        cards = new ArrayList<>();
        this.columnType = columnType;
    }

    public void addCards(List<CardListResponseDto> cards) {
        this.cards.addAll(cards);
    }

    public String getColumnType() {
        return columnType;
    }

    public List<CardListResponseDto> getCards() {
        return cards;
    }
}
