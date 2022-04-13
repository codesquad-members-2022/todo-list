package com.todolist.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.todolist.exception.ExceptionType;
import com.todolist.exception.GlobalException;
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

        if (allCards.size() == 0) {
            throw new GlobalException(ExceptionType.NO_USER_FOUNDED);
        }

        return allCards.stream()
            .map(card ->
                new CardInformationDto(card.getCardId(), card.getCardTitle(), card.getCardContent(), card.getBoardName()))
            .collect(Collectors.groupingBy(CardInformationDto::getBoardName));
    }

    public Integer save(CardCreateDto cardCreateDto) {
        return cardRepository.save(cardCreateDto.toCard());
    }

    public void delete(Integer cardId) {
        Integer deletedValue = cardRepository.delete(cardId);
        if (deletedValue == 0) {
            throw new GlobalException(ExceptionType.NO_FOUND_CARD);
        }
    }

    public void patch(Integer cardId, CardPatchDto cardPatchDto) {
        findCard(cardId);
        cardRepository.patch(cardId, cardPatchDto);
    }

    public CardInformationDto findCard(Integer cardId) {
        CardInformationDto cardInformationDto;
        try {
            cardInformationDto = cardRepository.findCard(cardId);
        } catch(EmptyResultDataAccessException emptyResultDataAccessException) {
            throw new NotFoundCardException(ExceptionType.NO_FOUND_CARD, "cardId");
        }
        return cardInformationDto;
    }
}
