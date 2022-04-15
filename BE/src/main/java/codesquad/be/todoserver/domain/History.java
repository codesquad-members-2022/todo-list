package codesquad.be.todoserver.domain;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class History {

	private Long id;
	private final Long todoId;
	private final String todoTitle;
	private final String user;
	private final String action;
	private final String fromStatus;
	private final String toStatus;
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
		return action.createHistoryBy(todo);
	}

}
