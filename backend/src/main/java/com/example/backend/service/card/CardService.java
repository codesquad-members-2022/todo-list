package com.example.backend.service.card;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

import com.example.backend.controller.card.dto.CardDto;
import com.example.backend.controller.card.dto.CardSaveResponse;
import com.example.backend.domain.card.Card;
import com.example.backend.domain.card.CardType;
import com.example.backend.repository.card.CardRepository;
import com.example.backend.repository.history.HistoryRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class CardService {

    private final CardRepository cardRepository;
    private final HistoryRepository historyRepository;

    public CardService(CardRepository cardRepository, HistoryRepository historyRepository) {
        this.cardRepository = cardRepository;
        this.historyRepository = historyRepository;
    }

    @Transactional
    public Card writeCard(CardDto cardDto) {
        Card card = cardRepository.save(cardDto.writeCard());
        return card;
    }

    public Map<CardType, List<CardReadResponse>> findAll() {
        List<Card> cards = cardRepository.findAll();
        return cards.stream()
                .map(CardReadResponse::new)
                .collect(groupingBy(CardReadResponse::getCardType, toList()));
    }


    public Card findById(Long id) {
        return cardRepository.findById(id).orElseThrow();
    }

    public Card updateCard(Long id, CardDto cardDto) {
        Card card = cardDto.updateCard(id);
        return cardRepository.update(card);
    }

    public void delete(Long id) {
        cardRepository.delete(id);
    }
}
