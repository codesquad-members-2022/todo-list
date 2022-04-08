package com.team05.todolist.domain;

public enum Event {
	CREATE("create"),
	MOVE("move"),
	DELETE("delete");

	private final String eventType;

	Event(String eventType) {
		this.eventType = eventType;
	}

	public String getEventType() {
		return this.eventType;
	}
}
