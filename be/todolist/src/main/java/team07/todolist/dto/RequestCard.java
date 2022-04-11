package team07.todolist.dto;

public class RequestCard {

	private String userId;
	private String title;
	private String content;
	private int row;
	private Integer status;

	public RequestCard() {
	}

	public RequestCard(String userId, String title, String content, int row, Integer status) {
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

	public int getRow() {
		return row;
	}

	public Integer getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "ResponseCard{" +
			"userId='" + userId + '\'' +
			", title='" + title + '\'' +
			", content='" + content + '\'' +
			", row=" + row +
			", status=" + status +
			'}';
	}
}
