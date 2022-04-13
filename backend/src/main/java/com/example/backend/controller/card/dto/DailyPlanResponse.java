package com.example.backend.controller.card.dto;

import com.example.backend.domain.card.CardType;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class DailyPlanResponse {

    private Map<CardType, List<Task>> cardTypeObjectMap;
    private Author author;

    public DailyPlanResponse() {};

    public DailyPlanResponse(List<Task> tasks) {
        cardTypeObjectMap = tasks.stream()
                .collect(groupingBy(Task::getCardType, toList()));
    }

    public Map<CardType, List<Task>> getCardTypeObjectMap() {
        return cardTypeObjectMap;
    }

    public Author getAuthor() {
        return author;
    }

    public void add(Author author) {
        this.author = author;
    }
}
