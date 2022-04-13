package com.team15.todoapi.repository;

import com.team15.todoapi.domain.Card;
import java.util.List;

public interface CardRepository {
	public List<Card> findAll();
}
