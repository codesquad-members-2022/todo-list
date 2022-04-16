package team07.todolist.dto;

public class RequestCard {

	private String userId;
	private String title;
	private String content;
	private int sequence;
	private Integer status;

	public RequestCard() {
	}

	public RequestCard(String userId, String title, String content, int sequence, Integer status) {
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.sequence = sequence;
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

	public int getSequence() {
		return sequence;
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
			", row=" + sequence +
			", status=" + status +
			'}';
	}
}
