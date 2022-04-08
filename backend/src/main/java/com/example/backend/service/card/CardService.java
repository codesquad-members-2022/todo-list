package com.example.backend.service.card;

import com.example.backend.controller.card.dto.CardDto;
import com.example.backend.domain.card.Card;
import com.example.backend.domain.card.CardType;
import com.example.backend.repository.card.CardRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    public Map<String, List<Card>> findAll() {
        return cardRepository.findAll();
    }

    public List<Card> findByType(CardType cardType) {
        return cardRepository.findByType(cardType);
    }
}
