package kr.codesquad.todolist.service;

import kr.codesquad.todolist.controller.CardDto;
import kr.codesquad.todolist.domain.Card;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class CardByStatus {
	private final Card.TodoStatus status;
	private final Long count;
	private final List<Card> cards;

	public List<CardDto.CardResponse> toResponse() {
		return cards.stream()
			.map(CardDto.CardResponse::new)
			.collect(Collectors.toList());
	}
}
