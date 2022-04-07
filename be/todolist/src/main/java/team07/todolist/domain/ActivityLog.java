package team07.todolist.domain;

import java.time.LocalDateTime;

public class ActivityLog {

	private Long id;
	private String title;
	private String type;
	private Integer previous;
	private Integer status;
	private LocalDateTime time;

	public ActivityLog(String title, String type, Integer previous, Integer status) {
		this.title = title;
		this.type = type;
		this.previous = previous;
		this.status = status;
		this.time = LocalDateTime.now();
	}

	public void setId(Long id) {
		this.id = id;
	}
}
