package com.todolist.project.service;

import com.todolist.project.domain.card.Card;
import com.todolist.project.domain.card.CardRepository;
import com.todolist.project.web.dto.CardAddDto;
import com.todolist.project.web.dto.CardListDto;
import com.todolist.project.web.dto.CardUpdateDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CardService {

	private final CardRepository cardRepository;

	public int addCard(CardAddDto cardAddDto) {
		return cardRepository.add(cardAddDto.toEntity());
	}

	public int removeCard(Long id) {
		return cardRepository.remove(id);
	}

	public List<CardListDto> findAll() {
		return cardRepository.findAll();
	}

	public List<CardListDto> findByStatus(String cardStatus) {
		return cardRepository.findCardsByStatus(cardStatus);
	}

	public int updateCard(Long id, CardUpdateDto cardUpdateDto) {
		return cardRepository.update(id, cardUpdateDto.toEntity());
	}

	public CardListDto findById(Long id) {
		Card card = cardRepository.findCardById(id);
		return new CardListDto(card.getId(), card.getCardIndex(), card.getTitle(),
			card.getContents(), card.getWriter(), String.valueOf(card.getCardStatus()),
			card.getCreatedTime());
	}
}
