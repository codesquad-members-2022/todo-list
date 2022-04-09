package com.example.backend.web.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Columns {
    private final Map<String, List<CardListResponseDto>> cardMap;

    public Columns() {
        cardMap = new HashMap<>();
    }

    public void addCardList(String columnName, List<CardListResponseDto> cardList) {
        cardMap.put(columnName, cardList);
    }

    public Map<String, List<CardListResponseDto>> getCardMap() {
        return cardMap;
    }
}
