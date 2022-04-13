package com.todolist.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.todolist.exception.ExceptionType;
import com.todolist.exception.NotFoundCardException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.todolist.domain.Card;
import com.todolist.domain.dto.CardCreateDto;
import com.todolist.domain.dto.CardInformationDto;
import com.todolist.domain.dto.CardPatchDto;
import com.todolist.repository.CardRepository;

@Service
public class CardService {

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public Map<String, List<CardInformationDto>> findAllCards(Integer userId) {
        List<Card> allCards = cardRepository.findAll(userId);

        return allCards.stream()
            .map(card ->
                new CardInformationDto(card.getCardId(), card.getCardTitle(), card.getCardContent(), card.getBoardName()))
            .collect(Collectors.groupingBy(CardInformationDto::getBoardName));
    }

    public Integer save(CardCreateDto cardCreateDto) {
        return cardRepository.save(cardCreateDto.toCard());
    }

    public void delete(Integer cardId) {
        cardRepository.delete(cardId);
    }

    public CardInformationDto findCard(Integer cardId) {
        return cardRepository.findCard(cardId);
    }

    public void patch(Integer cardId, CardPatchDto cardPatchDto) {
        try {
            CardInformationDto findCard = cardRepository.findCard(cardId);
        } catch(EmptyResultDataAccessException emptyResultDataAccessException) {

            throw new NotFoundCardException(ExceptionType.NO_FOUND_CARD, "cardId");
        }

        cardRepository.patch(cardId, cardPatchDto);
    }
}
