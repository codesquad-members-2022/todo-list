package kr.codesquad.todolist.controller.request;

import java.time.LocalDateTime;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import kr.codesquad.todolist.domain.Card;
import lombok.Data;
import lombok.NoArgsConstructor;

public class CardRequestDto {
	@Data
	@NoArgsConstructor
	public static class WriteRequest {
		@NotBlank(message = "제목은 필수 값입니다.")
		@Size(min = 1, max = 50, message = "50자 미안으로 작성 하세요.")
		private String subject;
		@NotBlank(message = "내용은 필수 값입니다.")
		@Size(min = 1, max = 500, message = "500자 미안으로 작성 하세요.")
		private String content;
		@NotBlank(message = "todo list 카테고리 정보는 필수 값입니다.")
		private String status;
		@NotNull(message = "사용자 id는 필수 값입니다.")
		@Min(1)
		private Long userId;

		public Card toEntity() {
			return Card.builder()
				.subject(subject)
				.content(content)
				.status(Card.TodoStatus.from(status))
				.createdAt(LocalDateTime.now())
				.userId(userId)
				.build();
		}
	}

	@Data
	@NoArgsConstructor
	public static class EditRequest {
		@NotBlank(message = "제목은 필수 값입니다.")
		@Size(min = 1, max = 50, message = "50자 미안으로 작성 하세요.")
		private String subject;
		@NotBlank(message = "내용은 필수 값입니다.")
		@Size(min = 1, max = 500, message = "500자 미안으로 작성 하세요.")
		private String content;
	}

	@Data
	@NoArgsConstructor
	public static class MoveRequest {
		@NotBlank(message = "이동할 위치의 todo list 카테고리 정보는 필수 값입니다.")
		private String toStatus;
		@NotNull(message = "이동할 위치의 순서 정보는 필수 값입니다.")
		private Long toOrder;
	}
}
