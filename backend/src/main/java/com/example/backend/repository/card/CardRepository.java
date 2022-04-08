package com.example.backend.repository.card;

import com.example.backend.domain.card.Card;
import com.example.backend.domain.card.CardType;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CardRepository {

    Card save(Card card);

    Map<String, List<Card>> findAll();

    List<Card> findByType(CardType cardType);

    Optional<Card> findById(Long id);
}
