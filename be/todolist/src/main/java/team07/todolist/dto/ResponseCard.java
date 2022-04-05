package team07.todolist.dto;

public class ResponseCard {

	private String userId;
	private String title;
	private String content;
	private Integer row;
	private int status;

	public ResponseCard(String userId, String title, String content, Integer row, int status) {
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.row = row;
		this.status = status;
	}

	public String getUserId() {
		return userId;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public Integer getRow() {
		return row;
	}

	public int getStatus() {
		return status;
	}
}
