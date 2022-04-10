package com.team05.todolist.domain.dto;

import lombok.Getter;

@Getter
public class ResponseDTO {

	private CardDTO card;
	private LogDTO log;

	public ResponseDTO(CardDTO cardDto, LogDTO logDto) {
		this.card = cardDto;
		this.log = logDto;
	}
}
