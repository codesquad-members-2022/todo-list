package com.todolist.project.web.dto;

import com.todolist.project.domain.ActionStatus;
import com.todolist.project.domain.CardStatus;
import com.todolist.project.domain.log.Log;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LogListDto {

	private String title;
	private String prevStatus;
	private String currentStatus;
	private String actionStatus;
	private LocalDateTime actionTime;

	public Log toEntity() {
		CardStatus prevStatus = CardStatus.valueOf(this.prevStatus);
		CardStatus currentStatus = CardStatus.valueOf(this.currentStatus);
		ActionStatus actionStatus = ActionStatus.valueOf(this.actionStatus);
		return new Log(title, prevStatus, currentStatus, actionStatus);
	}
}
