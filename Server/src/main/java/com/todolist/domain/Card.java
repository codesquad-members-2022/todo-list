package com.todolist.domain;

import java.time.LocalDateTime;

public class Card {

    private final Integer cardId;
    private final String cardTitle;
    private final String cardContent;
    private final LocalDateTime addDateTime;
    private final Integer internalOrder;
    private final String boardName;

    public Card(Integer cardId, String cardTitle, String cardContent,
                LocalDateTime addDateTime, Integer internalOrder, String boardName) {
        this.cardId = cardId;
        this.cardTitle = cardTitle;
        this.cardContent = cardContent;
        this.addDateTime = addDateTime;
        this.internalOrder = internalOrder;
        this.boardName = boardName;
    }

    public Integer getCardId() {
        return cardId;
    }

    public String getCardTitle() {
        return cardTitle;
    }

    public String getCardContent() {
        return cardContent;
    }

    public LocalDateTime getAddDateTime() {
        return addDateTime;
    }

    public Integer getInternalOrder() {
        return internalOrder;
    }

    public String getBoardName() {
        return boardName;
    }
}
