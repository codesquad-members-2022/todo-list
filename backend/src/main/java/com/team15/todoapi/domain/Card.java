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

}
