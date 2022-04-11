package com.team15.todoapi.domain;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Card {

	private Long id;
	private String title;
	private String content;
	private String userId;
	private LocalDateTime modifiedAt;
	private int section;

	//목록조회용
	public static Card of(Long id, String title, String content, String userId, LocalDateTime modifiedAt, int section) {
		return new Card(id, title, content, userId, modifiedAt, section);
	}
}
