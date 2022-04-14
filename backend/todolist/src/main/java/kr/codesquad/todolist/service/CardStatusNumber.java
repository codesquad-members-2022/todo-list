package kr.codesquad.todolist.service;

import kr.codesquad.todolist.domain.Card;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CardStatusNumber {
	private final Card.TodoStatus status;
	private final Long numberOfStatus;
}
