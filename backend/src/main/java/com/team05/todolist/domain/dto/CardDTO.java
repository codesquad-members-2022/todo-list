package com.team05.todolist.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
public class CardDTO {

	private Integer cardId;
	private Integer orderIndex;
	private String title;
	private String content;

	public CardDTO(Integer orderIndex, String title, String content) {
		this.orderIndex = orderIndex;
		this.title = title;
		this.content = content;
	}

	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}
}
