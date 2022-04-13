package com.team15.todoapi.controller.card;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CardRequest {

	private String title;
	private String content;
	private String userId;
	private int section;

}
