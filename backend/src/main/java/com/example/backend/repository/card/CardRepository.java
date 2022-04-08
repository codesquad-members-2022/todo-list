package com.example.backend.repository.card;

import com.example.backend.domain.card.Card;

import java.util.List;
import java.util.Map;

public interface CardRepository {

    Card save(Card card);

    Map<String, List<Card>> findAll();
}
