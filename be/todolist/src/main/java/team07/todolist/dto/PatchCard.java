package team07.todolist.dto;

public class PatchCard {

	private String title;
	private String content;

	public PatchCard() {
	}

	public PatchCard(String title, String content) {
		this.title = title;
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}
}
