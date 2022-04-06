package com.example.backend.domain;

import com.example.backend.web.dto.CardListResponseDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Column {
    private final Map<String, List<CardListResponseDto>> cardMap = new HashMap<>();

    public void addByStatus(String status, CardListResponseDto dto) {
        List<CardListResponseDto> cardList = cardMap.get(status);
        cardList.add(dto);
    }

    public Map<String, List<CardListResponseDto>> getCardMap() {
        return cardMap;
    }
}
