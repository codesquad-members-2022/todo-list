package com.todolist.service;

import com.todolist.domain.Card;
import com.todolist.repository.CardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public List<Card> findAllCards() {
        return cardRepository.findAllCards();
    }
}
