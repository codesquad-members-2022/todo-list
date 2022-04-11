package com.team15.todoapi.controller.card;

import com.team15.todoapi.domain.Card;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CardResponse {

	private Long id;
	private String title;
	private String content;
//	private Long memberId;
	private LocalDateTime modifiedAt;
	private int section;

	public static CardResponse from(Card card) {
		return new CardResponse(card.getId(), card.getTitle(), card.getContent(),
			card.getModifiedAt(), card.getSection());
	}

}
