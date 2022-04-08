package com.example.backend.service.card;

import com.example.backend.controller.card.dto.CardDto;
import com.example.backend.domain.card.Card;
import com.example.backend.repository.card.CardRepository;

import org.springframework.stereotype.Service;

@Service
public class CardService {

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public Card writeCard(CardDto cardDto) {
        Card card = new Card(cardDto.getTitle(), cardDto.getContent(), cardDto.getCardType());
        return cardRepository.save(card);
    }
}
