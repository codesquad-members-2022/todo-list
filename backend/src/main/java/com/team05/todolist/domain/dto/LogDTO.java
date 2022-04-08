package com.team05.todolist.domain.dto;

import com.team05.todolist.domain.Event;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class LogDTO {

	private Integer logId;
	private Event logEvent;
	private LocalDateTime logTime;
	private String title;
	private String prevSection;
	private String section;

	public LogDTO(Integer logId, String logEvent, LocalDateTime logTime, String title,
		String prevSection, String section) {
		this.logId = logId;
		this.logEvent = Event.valueOf(logEvent);
		this.logTime = checkDateTimeNull(logTime);
		this.title = title;
		this.prevSection = prevSection;
		this.section = section;
	}

	private LocalDateTime checkDateTimeNull(LocalDateTime logTime) {
		if (logTime == null) {
			return LocalDateTime.now();
		}
		return logTime;
	}
}
