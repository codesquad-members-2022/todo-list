package com.example.backend.service.card;

import com.example.backend.controller.card.dto.CompletedItem;
import com.example.backend.controller.card.dto.DailyPlan;
import com.example.backend.controller.card.dto.ProgressingItem;
import com.example.backend.controller.card.dto.TodoItem;
import com.example.backend.domain.card.CardType;
import com.example.backend.repository.card.jdbc.CardReadRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardReadService {

    private final CardReadRepository cardReadRepository;

    public CardReadService(CardReadRepository cardReadRepository) {
        this.cardReadRepository = cardReadRepository;
    }

    @Transactional(readOnly = true)
    public DailyPlan getDailyPlan() {
        List<TodoItem> todoItems = getTodoItems();
        List<ProgressingItem> progressingItems = getProgressingItems();
        List<CompletedItem> completedItems = getCompletedItems();
        return new DailyPlan(todoItems, progressingItems, completedItems);
    }

    private List<TodoItem> getTodoItems(){
        return cardReadRepository.findItemsByCardType(CardType.TODO).stream()
                .map(TodoItem::new)
                .collect(Collectors.toList());
    }

    private List<ProgressingItem> getProgressingItems() {
        return cardReadRepository.findItemsByCardType(CardType.PROGRESSING).stream()
                .map(ProgressingItem::new)
                .collect(Collectors.toList());
    }

    private List<CompletedItem> getCompletedItems() {
        return cardReadRepository.findItemsByCardType(CardType.COMPLETED).stream()
                .map(CompletedItem::new)
                .collect(Collectors.toList());
    }
}
