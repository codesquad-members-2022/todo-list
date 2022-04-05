package team07.todolist.dto;

import team07.todolist.domain.Card;

public class ResponseCardWithId {

	private Long id;
	private String userId;
	private String title;
	private String content;
	private Integer row;
	private Integer status;

	public ResponseCardWithId(Long id, String userId, String title, String content,
		Integer row, Integer status) {
		this.id = id;
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.row = row;
		this.status = status;
	}
}
