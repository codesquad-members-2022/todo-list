package com.example.backend.service.card;

import com.example.backend.controller.card.dto.DailyPlan;
import com.example.backend.repository.card.jdbc.CardReadRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CardReadService {

    private final CardReadRepository cardReadRepository;

    public CardReadService(CardReadRepository cardReadRepository) {
        this.cardReadRepository = cardReadRepository;
    }

    @Transactional(readOnly = true)
    public DailyPlan getDailyPlan() {
        return new DailyPlan(cardReadRepository.findItems());
    }
}
