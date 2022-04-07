package com.team05.todolist.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Card {

	private Integer id;
	private Integer orderIndex;
	private Integer delete;
	private String title;
	private String content;
	private Section section;

	public Card(Integer orderIndex, Integer delete, String title, String content, Section section) {
		this.orderIndex = orderIndex;
		this.delete = delete;
		this.title = title;
		this.content = content;
		this.section = section;
	}

}
