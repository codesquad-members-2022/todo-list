package com.example.todo.service;

import com.example.todo.controller.card.CardDto;
import com.example.todo.domain.Card;
import com.example.todo.repository.CardRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardService {

	private final CardRepository cardRepository;

	public List<Card> findAll() {
		return cardRepository.findAll();
	}
}
