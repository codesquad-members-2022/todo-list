package com.team05.todolist.domain;

import java.util.Arrays;

public enum Event {
	CREATE("create"),
	MOVE("move"),
	UPDATE("update"),
	DELETE("delete");

	private final String eventType;

	Event(String eventType) {
		this.eventType = eventType;
	}

	public String getEventType() {
		return this.eventType;
	}

	public static Event getEvent(String event) {
		return Arrays.stream(Event.values())
			.filter(c -> c.isSameEvent(event))
			.findAny()
			.orElseThrow();
	}

	private boolean isSameEvent(String event) {
		return this.eventType.equals(event);
	}
}
