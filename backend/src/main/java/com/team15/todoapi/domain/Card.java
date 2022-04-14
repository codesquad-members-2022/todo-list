package com.team15.todoapi.domain;

import com.team15.todoapi.controller.dto.card.CardRequest;
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
	private Long memberId;
	private LocalDateTime modifiedAt;
	private int section;

	//목록조회용
	public static Card of(Long id, String title, String content, Long memberId,
		LocalDateTime modifiedAt, int section) {
		return new Card(id, title, content, memberId, modifiedAt, section);
	}

	//카드 생성용
	public static Card of(CardRequest cardRequest, Long memberId) {
		return new Card(null, cardRequest.getTitle(), cardRequest.getContent(),
			memberId, null, cardRequest.getSection());
	}

	public void insertId(Card card, Long cardId){
		card.id = cardId;
	}

	public void insertModifiedAt(Card card, LocalDateTime now) {
		card.modifiedAt = now;
	}
}
