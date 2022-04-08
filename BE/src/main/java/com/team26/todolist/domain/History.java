package com.team26.todolist.domain;

import java.time.LocalDateTime;

public class History {

    private Long id;
    private CardAction cardAction;  // 필수
    private String userId;// 필수
    private String cardTitle;// 필수
    private String cardTitleBefore;
    private CardStatus cardStatus;// 필수
    private CardStatus cardStatusBefore;
    private LocalDateTime createdAt;// 필수

    public void initId(Long id) {
        this.id = id;
    }

    private History(HistoryBuilder historyBuilder) {
        this.cardAction = historyBuilder.cardAction;
        this.userId = historyBuilder.userId;
        this.cardTitle = historyBuilder.cardTitle;
        this.cardTitleBefore = historyBuilder.cardTitleBefore;
        this.cardStatus = historyBuilder.cardStatus;
        this.cardStatusBefore = historyBuilder.cardStatusBefore;
        this.createdAt = historyBuilder.createdAt;
    }

    public static HistoryBuilder builder(CardAction cardAction, String userId, String cardTitle,
            CardStatus cardStatus, LocalDateTime createdAt) {
        return new HistoryBuilder(cardAction, userId, cardTitle, cardStatus, createdAt);
    }

    public static class HistoryBuilder {
        private CardAction cardAction;
        private String userId;
        private String cardTitle;
        private String cardTitleBefore;
        private CardStatus cardStatus;
        private CardStatus cardStatusBefore;
        private LocalDateTime createdAt;

        public HistoryBuilder(CardAction cardAction, String userId, String cardTitle,
                CardStatus cardStatus, LocalDateTime createdAt) {
            this.cardAction = cardAction;
            this.userId = userId;
            this.cardTitle = cardTitle;
            this.cardStatus = cardStatus;
            this.createdAt = createdAt;
        }

        public HistoryBuilder cardTitleBefore(String cardTitleBefore) {
            this.cardTitleBefore = cardTitleBefore;
            return this;
        }

        public HistoryBuilder cardStatusBefore(CardStatus cardStatusBefore) {
            this.cardStatusBefore = cardStatusBefore;
            return this;
        }

        public History build() {
            return new History(this);
        }
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
}
