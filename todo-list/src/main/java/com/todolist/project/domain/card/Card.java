package com.todolist.project.domain.card;

import com.todolist.project.domain.CardStatus;
import com.todolist.project.web.dto.CardListResponseDto;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Card {
    private Long id;
    private int cardIndex;
    private String title;
    private String contents;
    private String writer;
    private LocalDateTime createdTime;
    private CardStatus cardStatus;

    public Card(Long id, int cardIndex, String title, String contents, String writer, LocalDateTime createdTime, CardStatus cardStatus) {
        this.id = id;
        this.cardIndex = cardIndex;
        this.title = title;
        this.contents = contents;
        this.writer = writer;
        this.createdTime = createdTime;
        this.cardStatus = cardStatus;
    }

	public Card(int cardIndex, String title, String contents, String writer,
		CardStatus cardStatus) {
		this.cardIndex = cardIndex;
		this.title = title;
		this.contents = contents;
		this.writer = writer;
		this.cardStatus = cardStatus;
	}

	public Card(int cardIndex, String title, String contents, CardStatus cardStatus) {
		this.cardIndex = cardIndex;
		this.title = title;
		this.contents = contents;
		this.cardStatus = cardStatus;
		this.createdTime = LocalDateTime.now();
	}

	public CardListResponseDto toEntity() {
		return new CardListResponseDto(id, cardIndex, title, contents, writer, cardStatus.name(),
			createdTime);
	}
}
