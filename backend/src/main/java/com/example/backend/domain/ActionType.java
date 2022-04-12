package com.example.backend.domain;

public enum ActionType {
	ADD("add"), UPDATE("update"), REMOVE("remove"), MOVE("move");

	private final String name;

	ActionType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
