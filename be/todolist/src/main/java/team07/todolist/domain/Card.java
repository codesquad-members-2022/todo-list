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
	private Integer row;
	private Integer status;
	private boolean isDeleted;

	public Card(String userId, String title, String content, Integer row, Integer status) {
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.row = row;
		this.status = status;
	}

	public void delete() {
		isDeleted = true;
	}

	public void setId(Long id) {
		this.id = id;
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

	public boolean isValid() {
		return !isDeleted;
	}

	public ResponseCard createResponseCard() {
		return new ResponseCard(id, userId, title, content, row, status);
	}

	public boolean isDifferentStatus(Integer status) {
		return !this.status.equals(status);
	}

	@Override
	public String toString() {
		return "Card{" +
			"id=" + id +
			", userId='" + userId + '\'' +
			", title='" + title + '\'' +
			", content='" + content + '\'' +
			", row=" + row +
			", status=" + status +
			", isDeleted=" + isDeleted +
			'}';
	}
}
