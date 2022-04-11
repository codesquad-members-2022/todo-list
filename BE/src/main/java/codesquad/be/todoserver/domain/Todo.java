package codesquad.be.todoserver.domain;

import java.time.LocalDateTime;

public class Todo {

	private Long id;
	private final String title;
	private final String contents;
	private final String user;
	private final String status;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public Todo(Long id, String title, String contents, String user, String status) {
		this.id = id;
		this.title = title;
		this.contents = contents;
		this.user = user;
		this.status = status;
	}

	public Todo(String title, String contents, String user, String status) {
		this.title = title;
		this.contents = contents;
		this.user = user;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getContents() {
		return contents;
	}

	public String getUser() {
		return user;
	}

	public String getStatus() {
		return status;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
}
