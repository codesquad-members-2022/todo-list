package codesquad.be.todoserver.domain;

public class History {

	private final Long id;
	private final Long todoId;
	private final String todoTitle;
	private final String action;
	private final String fromStatus;
	private final String toStatus;
	private final String createdAt;

	public History(Long id, Long todoId, String todoTitle, String action, String fromStatus,
		String toStatus, String createdAt) {
		this.id = id;
		this.todoId = todoId;
		this.todoTitle = todoTitle;
		this.action = action;
		this.fromStatus = fromStatus;
		this.toStatus = toStatus;
		this.createdAt = createdAt;
	}

	public Long getId() {
		return id;
	}

	public Long getTodoId() {
		return todoId;
	}

	public String getTodoTitle() { return todoTitle; }

	public String getAction() {
		return action;
	}

	public String getFromStatus() {
		return fromStatus;
	}

	public String getToStatus() {
		return toStatus;
	}

	public String getCreatedAt() {
		return createdAt;
	}
}
