package com.todolist.domain.dto;

import java.time.LocalDateTime;

import com.todolist.domain.Card;

import lombok.Getter;

@Getter
public class CardCreateDto {
    private Integer userId;
    private String cardTitle;
    private String cardContent;
    private String boardName;

    public CardCreateDto(Integer userId, String cardTitle, String cardContent, String boardName) {
        this.userId = userId;
        this.cardTitle = cardTitle;
        this.cardContent = cardContent;
        this.boardName = boardName;
    }

    public Card toCard() {
        return Card.builder()
            .userId(userId)
            .cardTitle(cardTitle)
            .cardContent(cardContent)
            .boardName(boardName)
            .createdTime(LocalDateTime.now())
            .build();
    }
}
