package com.team15.todoapi.domain;

public enum CardAction {
	ADD(1),
	REMOVE(2),
	UPDATE(3),
	MOVE(4);

	private final int code;

	CardAction(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
