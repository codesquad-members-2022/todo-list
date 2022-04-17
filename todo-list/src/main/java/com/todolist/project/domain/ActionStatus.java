package com.todolist.project.domain;

public enum ActionStatus {
	ADD("등록"), MOVE("이동"), UPDATE("수정"), REMOVE("삭제");

	public final String value;

	ActionStatus(String value) {
		this.value = value;
	}
}
