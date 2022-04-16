package team07.todolist.dto;

public class ResponseCard {

	private Long id;
	private String userId;
	private String title;
	private String content;
	private Integer sequence;
	private Integer status;

	public ResponseCard(Long id, String userId, String title, String content,
		Integer sequence, Integer status) {
		this.id = id;
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.sequence = sequence;
		this.status = status;
	}

	public Long getId() {
		return id;
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

	public Integer getSequence() {
		return sequence;
	}

	public Integer getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "ResponseCard{" +
			"id=" + id +
			", userId='" + userId + '\'' +
			", title='" + title + '\'' +
			", content='" + content + '\'' +
			", row=" + sequence +
			", status=" + status +
			'}';
	}
}
