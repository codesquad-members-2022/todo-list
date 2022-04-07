package kr.codesquad.todolist.card;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
@Builder
public class Card {
	private Long todoId;
	private String subject;
	private String content;
	private TodoStatus todoStatus;
	private Long todoOrder;
	private boolean deleted;
	private Long userId;

	@Getter
	@RequiredArgsConstructor
	public enum TodoStatus {
		TODO("todo", 1),
		ONGOING("ongoing",2),
		COMPLETED("completed",3);

		private final String text;
		private final int number;

		public TodoStatus from(int number) {
			return Arrays.stream(values())
				.filter(v -> v.number == number)
				.findAny()
				.orElseThrow(() -> new IllegalArgumentException("잘못된 todoStatus number 입니다."));
		}
	}

	public Long getTodoId() {
		return todoId;
	}

	public String getSubject() {
		return subject;
	}

	public String getContent() {
		return content;
	}

	public TodoStatus getTodoStatus() {
		return todoStatus;
	}

	public Long getTodoOrder() {
		return todoOrder;
	}

	public Long getUserId() {
		return userId;
	}
}
