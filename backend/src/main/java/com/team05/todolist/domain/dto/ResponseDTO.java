package com.team05.todolist.domain.dto;

import lombok.Getter;

@Getter
public class ResponseDTO {

	private CardDTO card;
	private LogDTO log;
	private String author;

	private ResponseDTO() {
	}
	
	public ResponseDTO(CardDTO cardDto, LogDTO logDto) {
		this.card = cardDto;
		this.log = logDto;
	}

	// 등록, 수정, 이동시 user-agent정보를 받기 위함.
	public ResponseDTO(CardDTO cardDto, LogDTO logDto, String author) {
		this.card = cardDto;
		this.log = logDto;
		this.author = author;
	}
}
