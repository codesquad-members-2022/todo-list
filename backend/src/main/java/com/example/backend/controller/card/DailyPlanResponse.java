package com.example.backend.controller.card;

import com.example.backend.domain.card.Card;
import com.example.backend.domain.card.CardType;
import org.springframework.scheduling.config.Task;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class DailyPlanResponse {

    private Map<CardType, List<Card>> cardTypeObjectMap;
    private Author author;

    public DailyPlanResponse() {};

    public DailyPlanResponse(List<Card> tasks) {
        cardTypeObjectMap = tasks.stream()
                .collect(groupingBy(Card::getCardType, toList()));
    }

    public Map<CardType, List<Card>> getCardTypeObjectMap() {
        return cardTypeObjectMap;
    }

    public Author getAuthor() {
        return author;
    }

    public void add(Author author) {
        this.author = author;
    }
}
