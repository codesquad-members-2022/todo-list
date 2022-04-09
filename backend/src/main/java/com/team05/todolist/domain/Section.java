package com.team05.todolist.domain;

import java.util.Arrays;
import java.util.Optional;

public enum Section {
	TODO("todo"),
	DOING("doing"),
	DONE("done");

	private final String sectionType;

	Section(String sectionType) {
		this.sectionType = sectionType;
	}

	public String getSectionType() {
		return sectionType;
	}

	public static Section getSection(String section) {
		return Arrays.stream(Section.values())
			.filter(c -> c.isSameSection(section))
			.findAny()
			.orElseThrow();
	}

	private boolean isSameSection(String section) {
		return this.sectionType.equals(section);
	}
}
