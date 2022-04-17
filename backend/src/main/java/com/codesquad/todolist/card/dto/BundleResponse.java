package com.codesquad.todolist.card.dto;

import com.codesquad.todolist.history.dto.HistoryResponse;

public class BundleResponse {

    private final CardResponse card;
    private final HistoryResponse history;

    private BundleResponse(CardResponse card, HistoryResponse history) {
        this.card = card;
        this.history = history;
    }

    public static BundleResponse of(CardResponse card, HistoryResponse history) {
        return new BundleResponse(card, history);
    }

    public CardResponse getCard() {
        return card;
    }

    public HistoryResponse getHistory() {
        return history;
    }
}
