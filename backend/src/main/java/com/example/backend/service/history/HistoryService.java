package com.example.backend.service.history;

import com.example.backend.controller.history.HistoryResponse;
import com.example.backend.controller.history.dto.HistorySaveRequest;
import com.example.backend.domain.history.History;
import com.example.backend.repository.history.HistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryService {

    private final HistoryRepository historyRepository;

    public HistoryService(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public List<HistoryResponse> findHistories(Long memberId, Long cardId) {
        return historyRepository.findHistories(memberId, cardId);
    }

    public History saveHistory(HistorySaveRequest request) {
        return historyRepository.save(request.toEntity());
    }
}
