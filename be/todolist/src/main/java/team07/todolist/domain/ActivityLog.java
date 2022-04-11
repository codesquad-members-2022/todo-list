package team07.todolist.domain;

import java.time.LocalDateTime;

public class ActivityLog {

	private Long id;
	private String title;
	private int type;
	private Integer previous;
	private Integer status;
	private LocalDateTime time;

	public ActivityLog(String title, int type, Integer previous, Integer status) {
		this.title = title;
		this.type = type;
		this.previous = previous;
		this.status = status;
		this.time = LocalDateTime.now();
	}

	public ActivityLog(ActivityLog activityLog, long id) {
		this.id = id;
		this.title = activityLog.title;
		this.type = activityLog.type;
		this.previous = activityLog.previous;
		this.status = activityLog.status;
		this.time = activityLog.time;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public int getType() {
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

}
