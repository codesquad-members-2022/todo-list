package com.team26.todolist.service;

import com.team26.todolist.domain.Card;
import com.team26.todolist.domain.CardAction;
import com.team26.todolist.dto.response.HistoryResponse;
import java.util.List;

public interface HistoryService {

    List<HistoryResponse> findHistories();
    void saveHistory(CardAction cardAction, String userId, Card cardBefore, Card cardNow);
}
