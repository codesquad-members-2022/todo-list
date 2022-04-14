package codesquad.be.todoserver.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class History {

	private Long id;
	private final Long todoId;
	private final String todoTitle;
	private final String user;
	private final String action;
	private final String fromStatus;
	private final String toStatus;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
	private final LocalDateTime createdAt;

	public History(Long id, Long todoId, String todoTitle, String user, String action,
		String fromStatus, String toStatus, LocalDateTime createdAt) {
		this.id = id;
		this.todoId = todoId;
		this.todoTitle = todoTitle;
		this.user = user;
		this.action = action;
		this.fromStatus = fromStatus;
		this.toStatus = toStatus;
		this.createdAt = createdAt;
	}

	public History(Long todoId, String todoTitle, String user, String action,
		String fromStatus, String toStatus) {
		this.todoId = todoId;
		this.todoTitle = todoTitle;
		this.user = user;
		this.action = action;
		this.fromStatus = fromStatus;
		this.toStatus = toStatus;
		this.createdAt = LocalDateTime.now();
	}

	public static History of(Todo todo, Action action) {
		switch (action) {
			case ADD:
				return new History(todo.getId(), todo.getTitle(), todo.getUser(), "add",
					"", todo.getStatus());
			case REMOVE:
				return new History(todo.getId(), todo.getTitle(), todo.getUser(), "remove",
					"", todo.getStatus());
			case MOVE:
			case UPDATE:
				break;
		}
		return null;
	}

}
