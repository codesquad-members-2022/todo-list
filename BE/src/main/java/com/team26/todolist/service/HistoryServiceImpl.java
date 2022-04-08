package com.team26.todolist.service;

import com.team26.todolist.domain.Card;
import com.team26.todolist.domain.CardAction;
import com.team26.todolist.domain.History;
import com.team26.todolist.dto.response.HistoryResponse;
import com.team26.todolist.repository.HistoryRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository historyRepository;

    public HistoryServiceImpl(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    @Override
    public List<HistoryResponse> findHistories() {
        List<History> histories = historyRepository.findAll();
        return histories.stream().map(HistoryResponse::of).collect(Collectors.toList());
    }

    @Override
    public void saveHistory(CardAction cardAction, Card cardBefore, Card cardNow) {
        // TODO : Card 클래스에 getter 필요
        History history = History.builder(cardAction, cardNow.getUserId(), cardNow.getCardTitle(),
                        cardNow.getCardStatus(), LocalDateTime.now())
                .cardStatusBefore(cardBefore.getCardStatus())
                .cardTitleBefore(cardBefore.getCardTitle())
                .build();
        historyRepository.save(history);
    }
}
