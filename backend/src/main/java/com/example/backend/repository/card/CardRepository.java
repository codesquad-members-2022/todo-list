package com.example.backend.repository.card;

import com.example.backend.domain.card.Card;

import java.util.List;
import java.util.Optional;

public interface CardRepository {

    Card save(Card card);

    List<Card> findAll();

    Optional<Card> findById(Long id);

    Card update(Card card);

    void delete(Long id);
}
