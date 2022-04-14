package kr.codesquad.todolist.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Arrays;

@Builder
@AllArgsConstructor
public class Card {
	private Long cardId;
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

	public boolean isPositionedAt(TodoStatus toStatus, Long toOrder) {
		return toStatus.equals(this.status) && toOrder.equals(this.order);
	}

	public Long getCardId() {
		return cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	public String getSubject() {
		return subject;
	}

	public String getContent() {
		return content;
	}

	public TodoStatus getStatus() {
		return status;
	}

	public Long getOrder() {
		return order;
	}

	public void setOrder(Long order) {
		this.order = order;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public Long getUserId() {
		return userId;
	}

	public void modify(String subject, String content) {
		this.subject = subject;
		this.content = content;
	}
}
