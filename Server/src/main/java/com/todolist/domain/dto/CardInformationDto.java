package com.todolist.domain.dto;

public class CardInformationDto {

    private final String cardTitle;
    private final String cardContent;
    private final String boardName;

    public CardInformationDto(String cardTitle, String cardContent, String boardName) {
        this.cardTitle = cardTitle;
        this.cardContent = cardContent;
        this.boardName = boardName;
    }

    public String getCardTitle() {
        return cardTitle;
    }

    public String getCardContent() {
        return cardContent;
    }

    public String getBoardName() {
        return boardName;
    }
}
