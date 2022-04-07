package com.todolist.service;

import com.todolist.domain.BoardType;
import com.todolist.domain.Card;
import com.todolist.domain.dto.CardInformationDto;
import com.todolist.repository.CardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CardService {

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public Map<String, List<CardInformationDto>> findAllCards() {
        List<Card> allCards = cardRepository.findAllCards();

        return allCards.stream()
            .map(card ->
                new CardInformationDto(card.getCardTitle(), card.getCardContent(), card.getBoardName()))
            .collect(Collectors.groupingBy(CardInformationDto::getBoardName));
    }
}
