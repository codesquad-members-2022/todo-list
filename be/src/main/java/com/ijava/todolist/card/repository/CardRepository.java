package com.ijava.todolist.card.repository;

import com.ijava.todolist.card.domain.Card;

import java.util.List;
import java.util.Optional;

public interface CardRepository {

    Card save(Card card);
    Optional<Card> findById(Long id);
    Optional<List<Card>> findByColumnId(Long columnId);
    void deleteAll();
    Optional<Integer> getCountOfCardsOnColumns(Long columnsId);
}
