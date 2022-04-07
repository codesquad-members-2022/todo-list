package com.example.backend.controller.card;

import com.example.backend.domain.card.CardType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CardReadService {

    private final CardReadRepository cardReadRepository;

    public CardReadService(CardReadRepository cardReadRepository) {
        this.cardReadRepository = cardReadRepository;
    }

    // TODO: 타입 조회로 변경
    @Transactional(readOnly = true)
    public DailyPlan getDailyPlan() {
        List<TodoItem> todoItems = cardReadRepository.findItemsTodoItems(CardType.TODO);
        List<HaveDoneItem> haveDoneItems = cardReadRepository.findHaveDoneItems(CardType.DONE);
        List<DoingItem> doingItems = cardReadRepository.findDoingItems(CardType.DOING);

        return null;
    }
}
