package com.team26.todolist.service;

import com.team26.todolist.domain.CardStatus;
import com.team26.todolist.dto.request.CardDeleteRequest;
import com.team26.todolist.dto.request.CardMoveRequest;
import com.team26.todolist.dto.request.CardRegistrationRequest;
import com.team26.todolist.dto.request.CardUpdateRequest;
import com.team26.todolist.dto.response.CardResponse;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardServiceMock implements CardService {

    List<CardResponse> cards = new ArrayList<>();

    public CardServiceMock() {
        initCards();
    }

    private void initCards() {
        cards.add(new CardResponse(1L, "damon", "github 공부하기", "add, commit, push 공부", CardStatus.TODO));
        cards.add(new CardResponse(2L, "ader", "공부하기", "뭐든 좀 하기", CardStatus.ONGOING));
        cards.add(new CardResponse(3L, "honux", "운동하기", "주짓수 하러가기", CardStatus.DONE));
    }

    @Override
    public List<CardResponse> findByCardStatus(String cardStatus) {
        return cards;
    }

    @Override
    public CardResponse addCard(CardRegistrationRequest cardRegistrationRequest) {
        return cards.get(0);
    }

    @Override
    public CardResponse modifyCard(CardUpdateRequest cardUpdateRequest) {
        return cards.get(1);
    }

    @Override
    public CardResponse changeCardStatus(CardMoveRequest cardMoveRequest) {
        return cards.get(2);
    }

    @Override
    public void deleteCard(CardDeleteRequest cardDeleteRequest) {

    }
}
