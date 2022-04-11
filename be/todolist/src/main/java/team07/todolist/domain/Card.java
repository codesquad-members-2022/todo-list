package team07.todolist.domain;

import team07.todolist.dto.ResponseCard;

public class Card {

	private static final int TODO = 1;
	private static final int PROGRESS = 2;
	private static final int DONE = 3;

	private Long id;
	private String userId;
	private String title;
	private String content;
	private int row;
	private int status;
	private boolean isDeleted;

	public Card(String userId, String title, String content, Integer row, Integer status) {
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.row = row;
		this.status = status;
	}

	public Card(Card card, Long id, Integer row, Integer status) {
		this.id = id;
		this.userId = card.userId;
		this.title = card.title;
		this.content = card.content;
		this.row = row;
		this.status = status;
	}

	public Card(Card card, String title, String content) {
		this.id = card.id;
		this.title = title;
		this.content = content;
		this.row = card.row;
		this.status = card.status;
	}

	public Card(Card card, Long id) {
		this.id = id;
		this.userId = card.userId;
		this.title = card.title;
		this.content = card.content;
		this.row = card.row;
		this.status = card.status;
	}

	public void delete() {
		isDeleted = true;
	}

	public Integer getRow() {
		return row;
	}

	public Integer getStatus() {
		return status;
	}

	public void decreaseRow() {
		this.row = this.row - 1;
	}

	public void increaseRow() {
		this.row = this.row + 1;
	}

	public boolean isValid() {
		return !isDeleted;
	}

	public ResponseCard createResponseCard() {
		return new ResponseCard(id, userId, title, content, row, status);
	}

}
