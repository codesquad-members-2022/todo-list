package kr.codesquad.todolist.card;

import java.time.LocalDateTime;
import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Card {
	private Long todoId;
	private String subject;
	private String content;
	private TodoStatus status;
	private Long order;
	private boolean deleted;
	private LocalDateTime createdAt;
	private Long userId;

	@Getter
	@RequiredArgsConstructor
	public enum TodoStatus {
		TODO("todo", 1),
		ONGOING("ongoing",2),
		COMPLETED("completed",3);

		private final String text;
		private final int code;

		public static TodoStatus from(int StatusCode) {
			return Arrays.stream(values())
				.filter(v -> v.code == StatusCode)
				.findAny()
				.orElseThrow(() -> new IllegalArgumentException("잘못된 todoStatus number 입니다."));
		}

		public static TodoStatus from(String statusText) {
			return Arrays.stream(values())
				.filter(v -> v.text.equals(statusText))
				.findAny()
				.orElseThrow(() -> new IllegalArgumentException("잘못된 todoStatus text 입니다."));
		}
	}
}
