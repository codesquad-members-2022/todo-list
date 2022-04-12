package kr.codesquad.todolist.card;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CardStatusNumber {
	private final Card.TodoStatus status;
	private final Long numberOfStatus;
}
