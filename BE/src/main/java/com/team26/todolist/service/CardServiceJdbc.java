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

    private final CardRepository cardRepository;
    private final HistoryService historyService;

    public CardServiceJdbc(CardRepository cardRepository, HistoryService historyService) {
        this.cardRepository = cardRepository;
        this.historyService = historyService;
    }

    @Override
    public List<CardResponse> findByCardStatus(String cardStatus) {
        return cardRepository.findByCardStatus(cardStatus).stream()
                .map(CardResponse::of)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public CardResponse addCard(CardRegistrationRequest cardRegistrationRequest) {
        Card saveCard = cardRepository.save(cardRegistrationRequest.toEntity());
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
    public CardResponse changeCardStatus(CardMoveRequest cardMoveRequest) {
        Card card = cardMoveRequest.toEntity();
        Card cardBefore = cardRepository.findById(cardMoveRequest.getId());
        Card cardAfter = cardRepository.updateCardStatus(card);

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
