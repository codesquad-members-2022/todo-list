package team07.todolist.domain;

import java.time.LocalDateTime;

public class ActivityLog {

	private Long id;
	private String title;
	private Integer type;
	private Integer previous;
	private Integer status;
	private LocalDateTime time;

	public ActivityLog(String title, Integer type, Integer previous, Integer status) {
		this.title = title;
		this.type = type;
		this.previous = previous;
		this.status = status;
		this.time = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Integer getType() {
		return type;
	}

	public Integer getPrevious() {
		return previous;
	}

	public Integer getStatus() {
		return status;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
