package com.team05.todolist.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardDTO {

	private Integer cardId;
	private Integer orderIndex;
	private String title;
	private String content;

	public CardDTO(Integer cardId, Integer orderIndex, String title, String content) {
		this.cardId = cardId;
		this.orderIndex = orderIndex;
		this.title = title;
		this.content = content;
	}
}
