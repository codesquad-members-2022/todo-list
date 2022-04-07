package com.team05.todolist.domain.dto;

import com.team05.todolist.domain.Event;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class LogDTO {

	private Integer logId;
	private Event logEvent;
	private LocalDateTime logTime;

	public LogDTO(Integer logId, Event logEvent, LocalDateTime logTime) {
		this.logId = logId;
		this.logEvent = logEvent;
		this.logTime = checkDateTimeNull(logTime);
	}

	private LocalDateTime checkDateTimeNull(LocalDateTime logTime) {
		if (logTime == null) {
			return LocalDateTime.now();
		}
		return logTime;
	}
}
