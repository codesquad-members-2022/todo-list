package com.team15.todoapi.controller.card;

import com.team15.todoapi.domain.Card;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class CardResponse {

	@Getter
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public static class ListInfo {

		private Long id;
		private String title;
		private String content;
		//	private Long memberId;
		private String modifiedAt;
		private int section;

		public static CardResponse.ListInfo from(Card card) {
			return new CardResponse.ListInfo(card.getId(), card.getTitle(), card.getContent(),
				card.getModifiedAt().toString(), card.getSection());
		}
	}

	@Getter
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public static class AddInfo {

		private Long id;
		private String modifiedAt;

		public static CardResponse.AddInfo from(Card card) {
			return new CardResponse.AddInfo(card.getId(), card.getModifiedAt().toString());
		}
	}
}
