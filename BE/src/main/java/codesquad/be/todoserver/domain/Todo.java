package codesquad.be.todoserver.domain;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

}
