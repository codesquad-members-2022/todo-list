package kr.codesquad.todolist.card;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;

public class CardDto {
	@Data
	@AllArgsConstructor
	public static class WriteRequest {
		@NotBlank(message = "제목은 필수 값입니다.")
		@Size(min = 1, max = 50, message = "50자 미안으로 작성 하세요.")
		private String subject;
		@NotBlank(message = "내용은 필수 값입니다.")
		@Size(min = 1, max = 500, message = "500자 미안으로 작성 하세요.")
		private String content;
		@NotBlank(message = "todo list 카테고리 정보는 필수 값입니다.")
		private String todoStatus;
		@NotBlank(message = "순서는 필수 값입니다.")
		private Long order;
		@NotBlank(message = "사용자 id는 필수 값입니다.")
		private Long userId;

		public Card toEntity() {
			return Card.builder()
				.subject(subject)
				.content(content)
				.todoStatus(Card.TodoStatus.valueOf(todoStatus))
				.todoOrder(order)
				.userId(userId)
				.build();
		}
	}

	@Data
	public static class WriteResponse {
		private Long todoId;
		private String subject;
		private String content;
		private String todoStatus;
		private Long order;
		private Long userId;

		public WriteResponse(Card card) {
			this.todoId = card.getTodoId();
			this.subject = card.getSubject();
			this.content = card.getContent();
			this.todoStatus = card.getTodoStatus().getText();
			this.order = card.getTodoOrder();
			this.userId = card.getUserId();
		}
	}
}
