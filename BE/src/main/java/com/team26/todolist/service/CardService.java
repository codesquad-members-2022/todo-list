package com.team26.todolist.service;

import com.team26.todolist.dto.request.CardChangeLocationRequest;
import com.team26.todolist.dto.request.CardDeleteRequest;
import com.team26.todolist.dto.request.CardRegistrationRequest;
import com.team26.todolist.dto.request.CardUpdateRequest;
import com.team26.todolist.dto.response.CardResponse;
import java.util.List;

public interface CardService {

    List<CardResponse> findByColumnId(Long columnId);

    CardResponse addCard(CardRegistrationRequest cardRegistrationRequest);

    CardResponse modifyCard(CardUpdateRequest cardUpdateRequest);

    CardResponse changeCardLocation(CardChangeLocationRequest cardChangeLocationRequest);

    void deleteCard(CardDeleteRequest cardDeleteRequest);

}
