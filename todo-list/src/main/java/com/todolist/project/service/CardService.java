package com.todolist.project.service;

import com.todolist.project.domain.card.Card;
import com.todolist.project.domain.card.CardRepository;
import com.todolist.project.web.dto.CardAddDto;
import com.todolist.project.web.dto.CardListRequestDto;
import com.todolist.project.web.dto.CardListResponseDto;
import com.todolist.project.web.dto.CardUpdateDto;
import java.util.ArrayList;
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

	public List<CardListResponseDto> findAll() {
		List<CardListResponseDto> dtos = new ArrayList<>();
		List<Card> cards = cardRepository.findAll();
		for (Card card : cards) {
			dtos.add(card.toEntity());
		}
		return dtos;
	}

	public List<CardListResponseDto> findByStatus(String cardStatus) {
		List<CardListResponseDto> dtos = new ArrayList<>();
		List<Card> cards = cardRepository.findCardsByStatus(cardStatus);
		for (Card card : cards) {
			dtos.add(card.toEntity());
		}
		return dtos;
	}

	public int updateCard(Long id, CardUpdateDto cardUpdateDto) {
		return cardRepository.update(id, cardUpdateDto.toEntity());
	}

	public CardListRequestDto findById(Long id) {
		Card card = cardRepository.findCardById(id);
		return new CardListRequestDto(card.getId(), card.getCardIndex(), card.getTitle(),
			card.getContents(), card.getWriter(), String.valueOf(card.getCardStatus()),
			card.getCreatedTime());
	}
}
