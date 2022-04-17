package com.team26.todolist.service;

import com.team26.todolist.domain.Card;
import com.team26.todolist.domain.CardAction;
import com.team26.todolist.dto.request.CardChangeLocationRequest;
import com.team26.todolist.dto.request.CardDeleteRequest;
import com.team26.todolist.dto.request.CardRegistrationRequest;
import com.team26.todolist.dto.request.CardUpdateRequest;
import com.team26.todolist.dto.response.CardResponse;
import com.team26.todolist.repository.CardRepository;
import com.team26.todolist.util.Constant;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class CardServiceJdbc implements CardService {

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
        Double newOrder = firstOrder != null ? firstOrder - Constant.ORDER_BASIC_DIFFERENCE : 0;
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
    public CardResponse changeCardLocation(CardChangeLocationRequest cardChangeLocationRequest) {
        Card card = cardChangeLocationRequest.toEntity();

        Card upperCard = cardRepository.findById(cardChangeLocationRequest.getUpperCardId());
        Card lowerCard = cardRepository.findById(cardChangeLocationRequest.getLowerCardId());
        card.setNewOrder(upperCard, lowerCard);

        Card cardBefore = cardRepository.findById(cardChangeLocationRequest.getId());
        Card cardAfter = cardRepository.updateLocation(card);

        historyService.saveHistory(CardAction.MOVE, "", cardBefore, cardAfter);

        return CardResponse.of(cardAfter);
    }

    @Override
    public void deleteCard(CardDeleteRequest cardDeleteRequest) {
        Card findCard = cardRepository.findById(cardDeleteRequest.getId());

        cardRepository.delete(findCard);
        historyService.saveHistory(CardAction.DELETE, "", findCard, null);
    }
}
