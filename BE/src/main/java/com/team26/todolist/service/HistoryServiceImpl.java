package com.team26.todolist.service;

import com.team26.todolist.domain.Card;
import com.team26.todolist.domain.CardAction;
import com.team26.todolist.domain.Column;
import com.team26.todolist.domain.History;
import com.team26.todolist.domain.HistoryFactory;
import com.team26.todolist.dto.response.HistoryResponse;
import com.team26.todolist.repository.HistoryRepository;
import java.util.List;
import java.util.Objects;
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
        Column columnBefore = getColumnOfCard(cardBefore);
        Column columnNow = getColumnOfCard(cardNow);

        if (Objects.equals(columnBefore, columnNow)) {
            return;
        }

        // TODO : Optional 사용 고려?
        History history = HistoryFactory.getHistory(cardAction, cardBefore, cardNow, columnBefore,
                columnNow);
        historyRepository.save(history);
    }

    private Column getColumnOfCard(Card card) {
        if (card == null) {
            return null;
        }

        return columnService.findById(card.getColumnId());
    }
}
