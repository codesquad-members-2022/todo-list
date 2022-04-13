package com.team15.todoapi.service;

import com.team15.todoapi.domain.Card;
import com.team15.todoapi.repository.CardRepository;
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
