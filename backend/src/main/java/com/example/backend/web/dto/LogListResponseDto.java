package com.example.backend.web.dto;

public class LogListResponseDto {
	private String title;
	private String prevColumnName;
	private String currentColumnName;
	private String actionType;
	private String createdDate;

	public LogListResponseDto(String title, String prevColumnName, String currentColumnName, String actionType,
		String createdDate) {
		this.title = title;
		this.prevColumnName = prevColumnName;
		this.currentColumnName = currentColumnName;
		this.actionType = actionType;
		this.createdDate = createdDate;
	}

	public String getTitle() {
		return title;
	}

	public String getPrevColumnName() {
		return prevColumnName;
	}

	public String getCurrentColumnName() {
		return currentColumnName;
	}

	public String getActionType() {
		return actionType;
	}

	public String getCreatedDate() {
		return createdDate;
	}
}
