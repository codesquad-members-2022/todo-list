package com.example.backend.web.dto;

public class LogListResponseDto {
	private String author;
	private String text;
	private String createdDate;

	public LogListResponseDto(String author, String text, String createdDate) {
		this.author = author;
		this.text = text;
		this.createdDate = createdDate;
	}

	public String getAuthor() {
		return author;
	}

	public String getText() {
		return text;
	}

	public String getCreatedDate() {
		return createdDate;
	}
}
