package com.team26.todolist.service;

import com.team26.todolist.domain.Card;
import com.team26.todolist.domain.CardAction;
import com.team26.todolist.dto.request.CardDeleteRequest;
import com.team26.todolist.dto.request.CardMoveRequest;
import com.team26.todolist.dto.request.CardRegistrationRequest;
import com.team26.todolist.dto.request.CardUpdateRequest;
import com.team26.todolist.dto.response.CardResponse;
import com.team26.todolist.repository.CardRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Primary
public class CardServiceJdbc implements CardService {

    public static final double DIFFERENCE = 1000.0;

    private final CardRepository cardRepository;
    private final HistoryService historyService;

    public CardServiceJdbc(CardRepository cardRepository, HistoryService historyService) {
        this.cardRepository = cardRepository;
        this.historyService = historyService;
    }

    @Override
    public List<CardResponse> findByColumnId(Long columnId) {
        return cardRepository.findByColumnId(columnId).stream().sorted()
                .map(CardResponse::of)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public CardResponse addCard(CardRegistrationRequest cardRegistrationRequest) {
        Double firstOrder = cardRepository.getFirstOrder();
        Double newOrder = firstOrder - DIFFERENCE;
        Card saveCard = cardRepository.save(cardRegistrationRequest.toEntity(), newOrder);
        historyService.saveHistory(CardAction.ADD, "", null, saveCard);

        return CardResponse.of(saveCard);
    }

    @Override
    public CardResponse modifyCard(CardUpdateRequest cardUpdateRequest) {
        Card card = cardUpdateRequest.toEntity();
        Card cardBefore = cardRepository.findById(cardUpdateRequest.getId());
        Card cardAfter = cardRepository.update(card);

        historyService.saveHistory(CardAction.UPDATE, "", cardBefore, cardAfter);

        return CardResponse.of(cardAfter);
    }

    @Override
    public CardResponse changeCardLocation(CardMoveRequest cardMoveRequest) {
        Double newOrder = getNewOrder(cardMoveRequest);
        Card card = cardMoveRequest.toEntity();
        Card cardBefore = cardRepository.findById(cardMoveRequest.getId());
        Card cardAfter = cardRepository.updateLocation(card, newOrder);

        historyService.saveHistory(CardAction.MOVE, "", cardBefore, cardAfter);

        return CardResponse.of(cardAfter);
    }

    @Override
    public void deleteCard(CardDeleteRequest cardDeleteRequest) {
        Card findCard = cardRepository.findById(cardDeleteRequest.getId());

        cardRepository.delete(findCard);
        historyService.saveHistory(CardAction.DELETE, "", findCard, null);
    }

    private double getNewOrder(CardMoveRequest cardMoveRequest) {
        Card upperCard = cardRepository.findById(cardMoveRequest.getUpperCardId());
        Card lowerCard = cardRepository.findById(cardMoveRequest.getLowerCardId());

        if (upperCard == null && lowerCard == null) {
            return 0.0;
        }

        if (upperCard == null) {
            return lowerCard.getOrder() - DIFFERENCE;
        }

        if (lowerCard == null) {
            return upperCard.getOrder() + DIFFERENCE;
        }

        return (upperCard.getOrder() + lowerCard.getOrder()) / 2;
    }
}
