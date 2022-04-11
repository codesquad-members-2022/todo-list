package com.team26.todolist.service;

import com.team26.todolist.dto.request.CardDeleteRequest;
import com.team26.todolist.dto.request.CardMoveRequest;
import com.team26.todolist.dto.request.CardRegistrationRequest;
import com.team26.todolist.dto.request.CardUpdateRequest;
import com.team26.todolist.dto.response.CardResponse;

import java.util.List;

public interface CardService {
    List<CardResponse> findByCardStatus(String cardStatus);

    CardResponse addCard(CardRegistrationRequest cardRegistrationRequest);

    CardResponse modifyCard(CardUpdateRequest cardUpdateRequest);

    CardResponse changeCardStatus(CardMoveRequest cardMoveRequest);

    void deleteCard(CardDeleteRequest cardDeleteRequest);
}
