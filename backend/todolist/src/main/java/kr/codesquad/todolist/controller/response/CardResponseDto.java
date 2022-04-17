package kr.codesquad.todolist.controller.response;

import kr.codesquad.todolist.domain.Card;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CardResponseDto {
	@Data
	public static class CardResponse {
		private Long cardId;
		private String subject;
		private String content;
		private String status;
		private Long order;
		private Long userId;

		public CardResponse(Card card) {
			this.cardId = card.getCardId();
			this.subject = card.getSubject();
			this.content = card.getContent();
			this.status = card.getStatus().getText();
			this.order = card.getOrder();
			this.userId = card.getUserId();
		}
	}

	@Data
	public static class Redirection {
		private Long cardId;

		public Redirection(Long cardId) {
			this.cardId = cardId;
		}
	}

	@Data
	public static class CardsResponse {
		private final Map<String, CardOfCountsAndListsResponse> data;

		public CardsResponse(
			Map<Card.TodoStatus, List<Card>> cardsInfo,
			Map<Card.TodoStatus, Long> numberOfCardsByStatus) {
			this.data = cardsInfo.keySet().stream()
				.collect(Collectors.toMap(
					status -> status.getText(),
					status -> new CardOfCountsAndListsResponse(numberOfCardsByStatus.get(status), cardsInfo.get(status))
				));
		}
	}

	@Data
	public static class CardOfCountsAndListsResponse {
		private Long count;
		private List<CardResponse> cards;

		public CardOfCountsAndListsResponse(Long count, List<Card> cards) {
			this.count = count;
			this.cards = cards.stream()
				.map(CardResponse::new)
				.collect(Collectors.toList());
		}
	}
}
