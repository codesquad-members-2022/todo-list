package com.example.backend.web.dto;

import com.example.backend.domain.ActionType;
import com.example.backend.domain.Log;

public class LogSaveRequestDto {
	private String title;
	private String prevColumnName;
	private String currentColumnName;
	private ActionType actionType;

	public LogSaveRequestDto(String title, String prevColumnName, String currentColumnName, ActionType actionType) {
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

	public ActionType getActionType() {
		return actionType;
	}

	public Log toEntity() {
		return Log.from(this);
	}
}
