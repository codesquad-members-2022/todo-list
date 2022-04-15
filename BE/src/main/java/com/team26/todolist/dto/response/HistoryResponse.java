package com.team26.todolist.dto.response;

import com.team26.todolist.domain.CardAction;
import com.team26.todolist.domain.History;
import java.time.LocalDateTime;

public class HistoryResponse {

    private CardAction cardAction;
    private String userId;
    private String cardTitle;
    private String cardTitleBefore;
    private String columnTitle;
    private String columnTitleBefore;
    private LocalDateTime createdAt;

    public HistoryResponse() {
    }

    public HistoryResponse(CardAction cardAction, String userId, String cardTitle,
            String cardTitleBefore, String columnTitle,
            String columnTitleBefore, LocalDateTime createdAt) {
        this.cardAction = cardAction;
        this.userId = userId;
        this.cardTitle = cardTitle;
        this.cardTitleBefore = cardTitleBefore;
        this.columnTitle = columnTitle;
        this.columnTitleBefore = columnTitleBefore;
        this.createdAt = createdAt;
    }

    public static HistoryResponse of(History history) {
        return new HistoryResponse(history.getCardAction(), history.getUserId(),
                history.getCardTitle(), history.getCardTitleBefore(),
                history.getColumnTitle(), history.getColumnTitleBefore(),
                history.getCreatedAt());
    }

    public CardAction getCardAction() {
        return cardAction;
    }

    public String getUserId() {
        return userId;
    }

    public String getCardTitle() {
        return cardTitle;
    }

    public String getCardTitleBefore() {
        return cardTitleBefore;
    }

    public String getColumnTitle() {
        return columnTitle;
    }

    public String getColumnTitleBefore() {
        return columnTitleBefore;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public History toEntity() {
        return History.builder(this.cardAction, this.userId, this.createdAt)
                .columnTitle(this.columnTitle)
                .columnTitleBefore(this.columnTitleBefore)
                .cardTitle(this.cardTitle)
                .cardTitleBefore(this.cardTitleBefore)
                .build();
    }
}
