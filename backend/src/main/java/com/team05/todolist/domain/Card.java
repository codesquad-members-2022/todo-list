package com.team05.todolist.domain;

public class Card {

	private Integer id;
	private Integer orderIndex;
	private Integer deleteYN;
	private String title;
	private String content;
	private Section section;

	public Card(Integer orderIndex, Integer deleteYN, String title, String content, String section) {
		this.orderIndex = orderIndex;
		this.deleteYN = deleteYN;
		this.title = title;
		this.content = content;
		this.section = Section.getSection(section);
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrderIndex() {
		return orderIndex;
	}

	public Integer getDeleteYN() {
		return deleteYN;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public String getSectionType() {
		return section.getSectionType();
	}
}
