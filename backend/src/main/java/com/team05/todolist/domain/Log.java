package com.team05.todolist.domain;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class Log {

	private Integer id;
	private Event event;
	private LocalDateTime logTime;

	public Log(Event event, LocalDateTime logTime) {
		this.event = event;
		this.logTime = logTime;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
