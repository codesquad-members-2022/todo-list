package kr.codesquad.todolist.card;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

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
