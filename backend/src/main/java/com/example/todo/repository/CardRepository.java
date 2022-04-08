package com.example.todo.repository;

import com.example.todo.domain.Card;
import java.util.List;

public interface CardRepository {
	public List<Card> findAll();
}
