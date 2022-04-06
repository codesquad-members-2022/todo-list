package com.ijava.todolist.card.service;

import com.ijava.todolist.card.domain.Card;
import com.ijava.todolist.card.exception.CardNotFoundException;
import com.ijava.todolist.card.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {

    private final static int CARD_COUNT_DEFAULT = 0;

    private final CardRepository cardRepository;

    public List<Card> findCardList(Long columnsId) {
        if (columnsId == null) return Collections.emptyList();

        return cardRepository.findByColumnId(columnsId)
                .orElseGet(Collections::emptyList);
    }

    public int getCountOfCardsOnColumns(Long columnsId) {
        return cardRepository.getCountOfCardsOnColumns(columnsId)
                .orElse(CARD_COUNT_DEFAULT);

    }

    public Card findCardById(Long id) {
        return cardRepository.findById(id)
                .orElseThrow(CardNotFoundException::new);
    }
}
