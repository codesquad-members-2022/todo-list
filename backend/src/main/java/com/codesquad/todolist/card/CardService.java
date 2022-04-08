package com.codesquad.todolist.card;

import com.codesquad.todolist.card.dto.CardCreateRequest;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public Card create(CardCreateRequest createRequest) {
        int count = cardRepository.countByColumn(createRequest.getColumnId());
        Card card = createRequest.toEntity(count + 1);
        return cardRepository.create(card);
    }
}
