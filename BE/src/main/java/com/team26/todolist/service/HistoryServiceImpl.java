package com.team26.todolist.service;

import com.team26.todolist.domain.Card;
import com.team26.todolist.domain.CardAction;
import com.team26.todolist.domain.Column;
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
    private final ColumnService columnService;

    public HistoryServiceImpl(HistoryRepository historyRepository,
            ColumnService columnService) {
        this.historyRepository = historyRepository;
        this.columnService = columnService;
    }

    @Override
    public List<HistoryResponse> findHistories() {
        List<History> histories = historyRepository.findAll();
        return histories.stream()
                .sorted()
                .map(HistoryResponse::of)
                .collect(Collectors.toList());
    }

    @Override
    public void saveHistory(CardAction cardAction, String userId, Card cardBefore, Card cardNow) {
        Column columnBefore =
                cardBefore != null ? columnService.findById(cardBefore.getColumnId()) : null;
        Column columnNow = cardNow != null ? columnService.findById(cardNow.getColumnId()) : null;

        if (cardBefore != null && cardNow != null && cardBefore.getColumnId()
                .equals(cardNow.getColumnId())) {
            return;
        }

        History history = History.builder(cardAction, userId, LocalDateTime.now())
                .cardTitle(columnNow != null ? cardNow.getTitle() : null)
                .cardTitleBefore(columnBefore != null ? cardBefore.getTitle() : null)
                .columnTitle(columnNow != null ? columnNow.getTitle() : null)
                .columnTitleBefore(columnBefore != null ? columnBefore.getTitle() : null)
                .build();
        historyRepository.save(history);
    }
}
