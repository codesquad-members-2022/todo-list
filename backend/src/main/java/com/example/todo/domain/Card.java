package com.example.todo.domain;

import java.time.LocalDateTime;

public class Card {

	private Long id;
	private String title;
	private String content;
	private Long member_id;
	private LocalDateTime created_at;
	private LocalDateTime modified_at;
	private boolean delete_flag;
	private int section;

	public Card(String title, String content) {
		this.title = title;
		this.content = content;
		this.created_at = LocalDateTime.now();
		this.modified_at = LocalDateTime.now();
		this.delete_flag = false;
		this.section = 1;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public Long getMember_id() {
		return member_id;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public LocalDateTime getModified_at() {
		return modified_at;
	}

	public boolean isDelete_flag() {
		return delete_flag;
	}

	public int getSection() {
		return section;
	}
}
