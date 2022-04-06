package com.todolist.domain;

import java.time.LocalDateTime;

public class Card {

    private Integer cardId;
    private String cardTitle;
    private String cardContent;
    private LocalDateTime addDateTime;
    private Integer internalOrder;
    private String boardName;
    private boolean remove;

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
