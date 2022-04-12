package codesquad.be.todoserver.domain;

import lombok.Getter;

@Getter
public class History {

	private final Long id;
	private final Long todoId;
	private final String todoTitle;
	private final String user;
	private final String action;
	private final String fromStatus;
	private final String toStatus;
	private final String createdAt;

	public History(Long id, Long todoId, String todoTitle, String user, String action,
		String fromStatus, String toStatus, String createdAt) {
		this.id = id;
		this.todoId = todoId;
		this.todoTitle = todoTitle;
		this.user = user;
		this.action = action;
		this.fromStatus = fromStatus;
		this.toStatus = toStatus;
		this.createdAt = createdAt;
	}

}
