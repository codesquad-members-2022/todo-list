package com.example.backend.controller.history;

import com.example.backend.domain.card.CardType;
import com.example.backend.domain.history.Action;

import java.time.LocalDateTime;

public class HistoryRead {
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private Action action;
    private Long memberId;
    private Long cardId;
    private String author;
    private CardType cardType;

    public HistoryRead(Long id, String content, LocalDateTime createdAt, String action, Long memberId, Long cardId, String author, String cardType) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.action = Action.valueOf(action);
        this.memberId = memberId;
        this.cardId = cardId;
        this.author = author;
        this.cardType = CardType.valueOf(cardType);
    }
}
