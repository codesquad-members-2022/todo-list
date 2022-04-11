package com.team05.todolist.domain.dto;

import java.util.List;
import lombok.Getter;

@Getter
public class ListResponseDTO {

	private ClassifiedCardsDTO classifiedCardsDTO;
	private List<LogDTO> logs;

	public ListResponseDTO(ClassifiedCardsDTO classifiedCardsDTO, List<LogDTO> logs) {
		this.classifiedCardsDTO = classifiedCardsDTO;
		this.logs = logs;
	}
}
