package com.todolist.project.service;

import com.todolist.project.domain.card.Card;
import com.todolist.project.domain.card.CardRepository;
import org.springframework.stereotype.Service;

@Service
public class CardService {
    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public void addCard(Card card) {

    }

    public void removeCard(int id) {
        cardRepository.remove(id);
    }
}
