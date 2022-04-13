package com.example.backend.controller.history;

import com.example.backend.domain.history.History;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryService {

    private final HistoryRepository historyRepository;

    public HistoryService(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public List<History> findHistories(Long memberId, Long cardId) {
        historyRepository.findHistories(memberId, cardId);
        return historyRepository.findHistories(memberId, cardId);
    }

    public History saveHistory(HistorySaveRequest request) {
        return historyRepository.save(request.toEntity());
    }
}
