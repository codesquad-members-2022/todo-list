package com.example.backend.repository.card;

import com.example.backend.domain.card.Card;
import com.example.backend.domain.card.CardType;

import java.util.List;
import java.util.Map;

public interface CardRepository {

    Card save(Card card);

    Map<String, List<Card>> findAll();

    List<Card> findByType(CardType cardType);
}
