package com.todolist.domain.dto;

import java.util.List;
import java.util.Map;

import lombok.Getter;

@Getter
public class CardAllShowDto {
    private final Map<String, List<CardInformationDto>> cardMap;

    public CardAllShowDto(Map<String, List<CardInformationDto>> cardMap) {
        this.cardMap = cardMap;
    }
}
