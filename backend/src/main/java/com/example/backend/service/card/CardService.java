package com.example.backend.service.card;

import com.example.backend.controller.card.dto.CardDto;
import com.example.backend.controller.history.HistorySaveRequest;
import com.example.backend.controller.history.HistoryService;
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

    @Transactional
    public Card writeCard(CardDto cardDto) {
        Card card = new Card(cardDto.getWriter(), cardDto.getTitle(), cardDto.getContent(), cardDto.getCardType(), cardDto.getMemberId());
        HistorySaveRequest historySaveRequest = new HistorySaveRequest();
        return cardRepository.save(card);
    }

    public Map<String, List<Card>> findAll() {
        return cardRepository.findAll();
    }


    public Card findById(Long id) {
        return cardRepository.findById(id).orElseThrow();
    }

    public Card updateCard(Long id, CardDto cardDto) {
        Card card = new Card(id, cardDto.getWriter(), cardDto.getTitle(), cardDto.getContent(), cardDto.getCardType());
        cardRepository.update(card);
        return null;
    }

    public Card delete(Long id) {
        cardRepository.delete(id);
        return null;
    }
}
