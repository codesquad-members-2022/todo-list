package com.example.backend.service.card;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

import com.example.backend.controller.card.dto.CardDto;
import com.example.backend.controller.history.dto.HistorySaveRequest;
import com.example.backend.service.history.HistoryService;
import com.example.backend.domain.card.Card;
import com.example.backend.domain.card.CardType;
import com.example.backend.repository.card.CardRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class CardService {

    private final CardRepository cardRepository;
    private final HistoryService historyService;

    public CardService(CardRepository cardRepository, HistoryService historyService) {
        this.cardRepository = cardRepository;
        this.historyService = historyService;
    }

    // 큰 트랜잭션
    @Transactional
    public Card writeCard(CardDto cardDto) {
        Card card = cardDto.writeCard();
        HistorySaveRequest historySaveRequest = new HistorySaveRequest();
        return cardRepository.save(card);
    }

    public Map<CardType, List<Card>> findAll() {
        List<Card> cards = cardRepository.findAll();
        return cards.stream().collect(groupingBy(Card::getCardType, toList()));
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
