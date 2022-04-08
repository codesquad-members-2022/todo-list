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
    public void saveHistory(CardAction cardAction, String userId, Card cardBefore, Card cardNow) {
        // TODO : Card 클래스에 getter 필요
        History history;
        if (cardAction == CardAction.ADD) {
            history = History.builder(cardAction, userId, LocalDateTime.now())
                    .cardTitle(cardNow.getCardTitle())
                    .cardStatus(cardNow.getCardStatus().name())
                    .build();
        } else if (cardAction == CardAction.DELETE) {
            history = History.builder(cardAction, userId, LocalDateTime.now())
                    .cardTitleBefore(cardBefore.getCardTitle())
                    .cardStatusBefore(cardBefore.getCardStatus().name())
                    .build();

        } else { // UPDATE, MOVE
            history = History.builder(cardAction, userId, LocalDateTime.now())
                    .cardTitle(cardNow.getCardTitle())
                    .cardTitleBefore(cardBefore.getCardTitle())
                    .cardStatus(cardNow.getCardStatus().name())
                    .cardStatusBefore(cardBefore.getCardStatus().name())
                    .build();
        }
        historyRepository.save(history);
    }
}
