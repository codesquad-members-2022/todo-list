package com.team15.todoapi.controller.dto.card;

import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CardRequest {

	@NotBlank
	private String title;
	@NotBlank
	private String content;
	@NotBlank
	private String userId;

	private int section;

}
