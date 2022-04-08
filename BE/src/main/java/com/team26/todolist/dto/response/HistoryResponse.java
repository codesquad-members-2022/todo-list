package com.team26.todolist.dto.response;

import com.team26.todolist.domain.CardAction;
import com.team26.todolist.domain.CardStatus;
import com.team26.todolist.domain.History;
import java.time.LocalDateTime;

public class HistoryResponse {

    private CardAction cardAction;  // 필수
    private String userId;// 필수
    private String cardTitle;// 필수
    private String cardTitleBefore;
    private CardStatus cardStatus;// 필수
    private CardStatus cardStatusBefore;
    private LocalDateTime createdAt;// 필수

    public HistoryResponse() {
    }

    public HistoryResponse(CardAction cardAction, String userId, String cardTitle,
            String cardTitleBefore, CardStatus cardStatus,
            CardStatus cardStatusBefore, LocalDateTime createdAt) {
        this.cardAction = cardAction;
        this.userId = userId;
        this.cardTitle = cardTitle;
        this.cardTitleBefore = cardTitleBefore;
        this.cardStatus = cardStatus;
        this.cardStatusBefore = cardStatusBefore;
        this.createdAt = createdAt;
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

    public CardStatus getCardStatus() {
        return cardStatus;
    }

    public CardStatus getCardStatusBefore() {
        return cardStatusBefore;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public History toEntity() {
        return History.builder(this.cardAction, this.userId, this.cardTitle,
                        this.cardStatus, this.createdAt)
                .cardStatusBefore(this.cardStatusBefore)
                .cardTitleBefore(this.cardTitleBefore)
                .build();
    }

    public static HistoryResponse of(History history) {
        return new HistoryResponse(history.getCardAction(), history.getUserId(),
                history.getCardTitle(), history.getCardTitleBefore(),
                history.getCardStatus(), history.getCardStatusBefore(),
                history.getCreatedAt());
    }
}
