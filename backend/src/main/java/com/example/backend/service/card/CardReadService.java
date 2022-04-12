package com.example.backend.service.card;

import com.example.backend.controller.card.dto.DailyPlanResponse;
import com.example.backend.controller.history.HistoryRepository;
import com.example.backend.repository.card.jdbc.CardReadRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CardReadService {

    private final CardReadRepository cardReadRepository;
    private final HistoryRepository historyRepository;

    public CardReadService(CardReadRepository cardReadRepository, HistoryRepository historyRepository) {
        this.cardReadRepository = cardReadRepository;
        this.historyRepository = historyRepository;
    }

    @Transactional(readOnly = true)
    public DailyPlanResponse getDailyPlan() {
        return new DailyPlanResponse(cardReadRepository.findItems());
    }
}
