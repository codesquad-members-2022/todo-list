package com.todolist.project.web.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CardListResponseDto {

	private Long id;
	private int cardIndex;
	private String title;
	private String contents;
	private String writer;
	private String cardStatus;
	private LocalDateTime createdTime;
}
