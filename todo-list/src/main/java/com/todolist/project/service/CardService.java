package com.todolist.project.service;

import com.todolist.project.domain.card.Card;
import com.todolist.project.domain.card.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CardService {
    private final CardRepository cardRepository;

    public int addCard(Card card) {
        return cardRepository.add(card);
    }

    public int removeCard(int id) {
        return cardRepository.remove(id);
    }
}
