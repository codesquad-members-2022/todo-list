package com.example.backend.repository.card;

import com.example.backend.domain.card.Card;
import com.example.backend.domain.card.CardType;

import java.util.List;
import java.util.Optional;

public interface CardRepository {

    Card save(Card card);

    List<Card> findAll();

    Optional<Card> findById(Long id);

    Card update(Card card);

    void delete(Long id);

    Card changePosition(Card card, CardType cardType, Long position);
}
