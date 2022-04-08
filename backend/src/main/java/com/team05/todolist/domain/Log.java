package com.team05.todolist.domain;

import java.time.LocalDateTime;

public class Log {

	private Integer id;
	private Event event;
	private LocalDateTime logTime;
	private String title;
	private String prevSection;
	private String section;

	public Log(Event event, LocalDateTime logTime, String title, String prevSection, String section) {
		this.event = event;
		this.logTime = logTime;
		this.title = title;
		this.prevSection = prevSection;
		this.section = section;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEvent() {
		return event.name();
	}

	public LocalDateTime getLogTime() {
		return logTime;
	}

	public String getTitle() {
		return title;
	}

	public String getPrevSection() {
		return prevSection;
	}

	public String getSection() {
		return section;
	}
}
