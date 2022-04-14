package com.team05.todolist.repository;

import com.team05.todolist.domain.Card;
import com.team05.todolist.domain.Section;
import java.util.List;
import java.util.Optional;

public interface CardRepository {

    int save(Card card);
    int delete(int id);
    List<Card> findAll();
    Optional<Card> findById(int id);
    Integer findNumberOfCards(String section);
	void move(Card moveTargetCard);
    List<Card> findBySection(Section todo);
}
