package com.team05.todolist.domain;

public class Card {

	private Integer id;
	private Integer order;
	private Integer deleteYN;
	private String title;
	private String content;
	private Section section;

	public Card(Integer order, Integer deleteYN, String title, String content, String section) {
		this.order = order;
		this.deleteYN = deleteYN;
		this.title = title;
		this.content = content;
		this.section = Section.getSection(section);
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public Integer getOrder() {
		return order;
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

	public void changeTitle(String title) {
		this.title = title;
	}

	public void changeOrder(Integer order) {
		this.order = order;
	}

	public void changeContent(String content) {
		this.content = content;
	}

	public void changeSection(String section) {
		this.section = Section.getSection(section);
	}
}
