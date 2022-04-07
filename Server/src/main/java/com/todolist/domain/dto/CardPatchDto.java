package com.todolist.domain.dto;

import lombok.Getter;

@Getter
public class CardPatchDto {

    private final String cardTitle;
    private final String cardContent;

    public CardPatchDto(String cardTitle, String cardContent) {
        this.cardTitle = cardTitle;
        this.cardContent = cardContent;
    }
}
