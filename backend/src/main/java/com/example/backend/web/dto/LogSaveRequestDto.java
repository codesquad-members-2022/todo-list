package com.example.backend.web.dto;

import com.example.backend.domain.ActionType;
import com.example.backend.domain.Log;

public class LogSaveRequestDto {
	private String title;
	private String prevColumnName;
	private String curColumnName;
	private ActionType actionType;

	public LogSaveRequestDto(String title, String prevColumnName, String curColumnName, ActionType actionType) {
		this.title = title;
		this.prevColumnName = prevColumnName;
		this.curColumnName = curColumnName;
		this.actionType = actionType;
	}

	public String getTitle() {
		return title;
	}

	public String getPrevColumnName() {
		return prevColumnName;
	}

	public String getCurColumnName() {
		return curColumnName;
	}

	public ActionType getActionType() {
		return actionType;
	}

	public Log toEntity() {
		return new Log.Builder().title(title).prevColumnName(prevColumnName).curColumnName(curColumnName).actionType(actionType).build();
	}
}
