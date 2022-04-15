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
	private Integer action;

	//목록조회용
	public static Card of(Long id, String title, String content, Long memberId,
		LocalDateTime modifiedAt, int section) {
		return new Card(id, title, content, memberId, modifiedAt, section, null);
	}

	//카드 생성용
	public static Card of(CardRequest cardRequest, Long memberId) {

		if(cardRequest.getId() == null){
			return new Card(null, cardRequest.getTitle(), cardRequest.getContent(),
				memberId, null, cardRequest.getSection(), null);
		}

		return new Card(cardRequest.getId(), cardRequest.getTitle(), cardRequest.getContent(),
			memberId, null, cardRequest.getSection(), null);
	}

	public void insertId(Card card, Long cardId){
		card.id = cardId;
	}

	public void insertModifiedAt(Card card, LocalDateTime now) {
		card.modifiedAt = now;
	}

	public void insertAction(Card card, int action){
		card.action = action;
	}

	@Override
	public String toString() {
		return "Card{" +
			"id=" + id +
			", title='" + title + '\'' +
			", content='" + content + '\'' +
			", memberId=" + memberId +
			", modifiedAt=" + modifiedAt +
			", section=" + section +
			", action=" + action +
			'}';
	}
}
