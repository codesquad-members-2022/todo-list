package team07.todolist.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class ResponseActivityLog {

	private Long id;
	private String title;
	private int type;
	private Integer previous;
	private Integer status;
	private String time;

	public ResponseActivityLog(Long id, String title, int type, Integer previous,
		Integer status, LocalDateTime time) {
		this.id = id;
		this.title = title;
		this.type = type;
		this.previous = previous;
		this.status = status;
		this.time = time.truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
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

	public String getTime() {
		return time;
	}
}
