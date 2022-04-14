package com.team05.todolist.domain;

public class Card {

	private final static Integer INCREMENT = 1000;
	private Integer id;
	private Integer order;
	private Integer deleteYN;
	private String title;
	private String content;
	private Section section;
	private String author;

	public Card(Integer order, Integer deleteYN, String title, String content, String section, String author) {
		this.order = order;
		this.deleteYN = deleteYN;
		this.title = title;
		this.content = content;
		this.section = Section.getSection(section);
		this.author = author;
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

	public String getAuthor() {
		return author;
	}

	public void changeTitle(String title) {
		this.title = title;
	}

	public void changeOrder(Integer order) {
		this.order = order;
	}

	public void changeOrder(Integer preOrder, Integer nextOrder) {
		if (preOrder == -1 && nextOrder != -1) {	// 맨 처음으로 카드를 옮길 때
			this.order = nextOrder / 2;
		}

		if (preOrder != -1 && nextOrder == -1){		// 맨 마지막으로 카드를 옮길 때
			this.order = preOrder + INCREMENT;
		}

		if (preOrder == -1 && nextOrder == -1) {	// 옮기려고 하는 섹션에 카드가 존재하지 않는 경우
			this.order = INCREMENT;
		}

		if (preOrder != -1 && nextOrder != -1) {	// 그 외
			this.order = (preOrder + nextOrder)/2;
		}
	}

	public void changeContent(String content) {
		this.content = content;
	}

	public void changeSection(String section) {
		this.section = Section.getSection(section);
	}

	public void changeAuthor(String userAgent) {
		this.author = author;
	}

	public void setOrder(int order) {
		this.order = order;
	}

}
