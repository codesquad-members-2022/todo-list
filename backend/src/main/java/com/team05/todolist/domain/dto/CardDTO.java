package com.team05.todolist.domain.dto;

import com.team05.todolist.domain.Section;
import lombok.Getter;

@Getter
public class CardDTO {

	private Integer cardId;
	private Integer orderIndex;
	private String title;
	private String content;
	private String section;

	public CardDTO(Integer orderIndex, String title, String content, String section) {
		this.orderIndex = orderIndex;
		this.title = title;
		this.content = content;
		this.section = section;
	}

	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}
}
