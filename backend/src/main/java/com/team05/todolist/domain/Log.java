package com.team05.todolist.domain;

import java.time.LocalDateTime;

public class Log {

	private Integer id;
	private Event event;
	private LocalDateTime logTime;
	private String title;
	private String prevSection;
	private Section section;

	public Log(String event, LocalDateTime logTime, String title, String section) {
		this(event, logTime, title, null, section);
	}

	public Log(String event, LocalDateTime logTime, String title, String prevSection, String section) {
		this.event = Event.getEvent(event);
		this.logTime = logTime;
		this.title = title;
		this.prevSection = prevSection;
		this.section = Section.getSection(section);
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public String getEventType() {
		return event.getEventType();
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

	public String getSectionType() {
		return section.getSectionType();
	}
}
