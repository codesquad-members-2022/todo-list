package com.ijava.todolist.card.service;

import com.ijava.todolist.card.controller.dto.*;
import com.ijava.todolist.card.domain.Card;
import com.ijava.todolist.history.Action;
import com.ijava.todolist.history.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CardActionService {

    private final CardService cardService;
    private final HistoryService historyService;

    public CardResponse add(CardCreateRequest createRequest) {
        Card savedCard = cardService.saveNewCard(createRequest);
        historyService.store(savedCard.getId(), savedCard.getColumnsId(), savedCard.getColumnsId(), Action.ADD);

        return CardResponse.from(savedCard);
    }

    public Card update(Long cardId, CardUpdateRequest updateRequest) {
        Card updatedCard = cardService.updateCard(cardId, updateRequest);
        historyService.store(updatedCard.getId(), updatedCard.getColumnsId(), updatedCard.getColumnsId(), Action.UPDATE);

        return updatedCard;
    }

    public CardMovedResponse move(CardMoveRequest cardMoveRequest) {
        Long oldColumnId = cardService.findCardById(cardMoveRequest.getCardId())
                .getId();

        Card movedCard = cardService.moveCard(cardMoveRequest);
        historyService.store(movedCard.getId(), oldColumnId, movedCard.getColumnsId(), Action.MOVE);

        return new CardMovedResponse(movedCard.getId(), oldColumnId, movedCard.getColumnsId());
    }

    public CardDeleteResponse delete(Long cardId) {
        Card deleteTargetCard = cardService.findCardById(cardId);
        historyService.store(deleteTargetCard.getId(), deleteTargetCard.getColumnsId(), deleteTargetCard.getColumnsId(), Action.REMOVE);

        return new CardDeleteResponse(cardService.deleteCard(cardId));
    }



}
