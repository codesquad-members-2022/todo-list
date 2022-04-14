package com.team15.todoapi.domain;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class History {

	private Long id;
	private LocalDateTime createdAt;
	private Long cardId;
	//	private Long memberId;
	private int oldSection;
	private int currentSection;
	private String action;
	private String title;

	public static History of(long id, LocalDateTime createdAt, long cardId,
		int oldSection, int currentSection, String action, String title) {
		return new History(id, createdAt, cardId, oldSection, currentSection, action, title);
	}
}
