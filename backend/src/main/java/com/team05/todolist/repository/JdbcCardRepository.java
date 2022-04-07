package com.team05.todolist.repository;

import com.team05.todolist.domain.Card;
import java.util.List;
import java.util.Optional;

public class JdbcCardRepository implements CardRepository {

    @Override
    public void save(Card card) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Card> findAll() {
        return null;
    }

    @Override
    public Optional<Card> findById(int id) {
        return Optional.empty();
    }
}
