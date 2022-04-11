package com.example.backend.web.dto;

import com.example.backend.domain.Log;

public class LogSaveRequestDto {
	private String title;
	private String prevColumnName;
	private String currentColumnName;
	private String actionType;

	public LogSaveRequestDto(String title, String prevColumnName, String currentColumnName, String actionType) {
		this.title = title;
		this.prevColumnName = prevColumnName;
		this.currentColumnName = currentColumnName;
		this.actionType = actionType;
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

	public Log toEntity() {
		return new Log.Builder()
			.title(title)
			.prevColumnName(prevColumnName)
			.currentColumnName(currentColumnName)
			.actionType(actionType)
			.build();
	}
}
