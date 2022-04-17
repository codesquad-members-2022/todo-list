package com.todolist.project.domain;

public enum CardStatus {
	TODO("해야할 일"), PROGRESS("하고있는 일"), DONE("완료한 일");

	public final String value;

	CardStatus(String value) {
		this.value = value;
	}
}
