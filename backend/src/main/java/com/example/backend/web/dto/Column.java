package com.example.backend.web.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Column {
    private final Map<String, List<CardListResponseDto>> cardMap = new HashMap<>();

    public void addByColumnName(String columnName, CardListResponseDto dto) {
        List<CardListResponseDto> cardList = cardMap.get(columnName);
        cardList.add(dto);
    }

    public Map<String, List<CardListResponseDto>> getCardMap() {
        return cardMap;
    }
}
