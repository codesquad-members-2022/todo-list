package com.team05.todolist.domain;

public enum Section {
	TODO("todo"),
	DOING("doing"),
	DONE("done");

	private final String sectionType;

	Section(String sectionType) {
		this.sectionType = sectionType;
	}
}
