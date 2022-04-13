package kr.codesquad.todolist.card;

import java.time.LocalDateTime;
import java.util.Arrays;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
public class Card {
	private Long cardId;
	private String subject;
	private String content;
	private TodoStatus status;
	private Long order;
	private boolean deleted;
	private LocalDateTime createdAt;
	private Long userId;

	public boolean isPositionedAt(TodoStatus toStatus, Long toOrder) {
		return toStatus.equals(this.status) && toOrder.equals(this.order);
	}

	@Getter
	@RequiredArgsConstructor
	public enum TodoStatus {
		TODO("todo"),
		ONGOING("ongoing"),
		COMPLETED("completed");

		public static final String ERROR_OF_TODO_STATUS_TEXT_TYPE = "잘못된 todoStatus text 입니다.";
		private final String text;

		public static TodoStatus from(String statusText) {
			return Arrays.stream(values())
				.filter(v -> v.text.equals(statusText))
				.findAny()
				.orElseThrow(() -> new IllegalArgumentException(ERROR_OF_TODO_STATUS_TEXT_TYPE));
		}
	}

	protected Long getCardId() {
		return cardId;
	}

	protected void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	protected String getSubject() {
		return subject;
	}

	protected String getContent() {
		return content;
	}

	protected TodoStatus getStatus() {
		return status;
	}

	protected Long getOrder() {
		return order;
	}

	public void setOrder(Long order) {
		this.order = order;
	}

	protected LocalDateTime getCreatedAt() {
		return createdAt;
	}

	protected Long getUserId() {
		return userId;
	}

	public void modify(String subject, String content) {
		this.subject = subject;
		this.content = content;
	}
}
