package com.team26.todolist.domain;

import java.time.LocalDateTime;

public class HistoryFactory {
    public static History getHistory(CardAction cardAction, Card cardBefore, Card cardNow, Column columnBefore, Column columnNow) {
        if (cardAction == CardAction.ADD) {
            return History.builder(cardAction, "", LocalDateTime.now())
                    .cardTitle(cardNow.getTitle())
                    .columnTitle(columnNow.getTitle())
                    .build();
        }

        if (cardAction == CardAction.DELETE) {
            return History.builder(cardAction, "", LocalDateTime.now())
                    .cardTitleBefore(cardBefore.getTitle())
                    .columnTitleBefore(columnBefore.getTitle())
                    .build();
        }

        return History.builder(cardAction, "", LocalDateTime.now())
                .cardTitle(cardNow.getTitle())
                .columnTitle(columnNow.getTitle())
                .cardTitleBefore(cardBefore.getTitle())
                .columnTitleBefore(columnBefore.getTitle())
                .build();
    }

}
