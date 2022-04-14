package com.hooria.todo.service;

import com.hooria.todo.aop.ExcludeLogging;
import com.hooria.todo.domain.Card;
import com.hooria.todo.dto.AddCardRequest;
import com.hooria.todo.dto.CardResponse;
import com.hooria.todo.dto.UpdateCardLayoutRequest;
import com.hooria.todo.dto.UpdateCardRequest;
import com.hooria.todo.error.CardRunTimeException;
import com.hooria.todo.error.ErrorCode;
import com.hooria.todo.repository.CardRepository;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

    @ExcludeLogging
    public List<CardResponse> selectAll() {
        return cardRepository.findAll().stream()
                .map(Card::toCardResponse)
                .collect(Collectors.toList());
    }

    public CardResponse add(AddCardRequest addCardRequest) {
        Card newCard = addCardRequest.toEntity();
        long addedId = cardRepository.add(newCard);
        return cardRepository.findById(addedId)
                .map(Card::toCardResponse)
                .orElseThrow(() -> new CardRunTimeException(ErrorCode.ADD_CARD_ERROR));
    }

    public CardResponse delete(long id) {
        long deletedId = cardRepository.delete(id);
        return cardRepository.findById(deletedId)
                .map(Card::toCardResponse)
                .orElseThrow(() -> new CardRunTimeException(ErrorCode.DELETE_CARD_ERROR));
    }

    public CardResponse update(UpdateCardRequest updateCardRequest) {
        Card updatedCard = updateCardRequest.toEntity();
        long updatedId = cardRepository.update(updatedCard);
        return cardRepository.findById(updatedId)
                .map(Card::toCardResponse)
                .orElseThrow(() -> new CardRunTimeException(ErrorCode.UPDATE_CARD_ERROR));
    }

    public List<CardResponse> updateCardsLayout(List<UpdateCardLayoutRequest> updateCardLayoutRequests) {
        for (UpdateCardLayoutRequest updateCardLayoutParam : updateCardLayoutRequests) {
            cardRepository.updateRowPositionById(updateCardLayoutParam.getId(), updateCardLayoutParam.getRowPosition());
        }
        return selectAll();
    }
}
