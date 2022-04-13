package com.team26.todolist.service;

import com.team26.todolist.dto.response.CardResponse;
import com.team26.todolist.repository.CardRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Primary
public class CardServiceJdbc {

    private final CardRepository cardRepository;

    public CardServiceJdbc(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public List<CardResponse> findCardsByCardStatus(String cardStatus) {
        return cardRepository.findByCardStatus(cardStatus).stream()
                .map(CardResponse::of)
                .collect(Collectors.toUnmodifiableList());
    }
}
