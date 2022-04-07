package com.todolist.project.service;

import com.todolist.project.domain.card.Card;
import com.todolist.project.domain.card.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CardService {
    private final CardRepository cardRepository;

    public void addCard(Card card) {
        cardRepository.add(card);
    }

    public void removeCard(int id) {
        cardRepository.remove(id);
    }
}
