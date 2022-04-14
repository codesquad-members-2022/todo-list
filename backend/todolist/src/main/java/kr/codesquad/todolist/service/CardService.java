package kr.codesquad.todolist.service;

import kr.codesquad.todolist.controller.CardDto;
import kr.codesquad.todolist.dao.CardDao;
import kr.codesquad.todolist.domain.Card;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CardService {
	public static final BiFunction<String, Long, String> ERROR_MESSAGE = String::format;
	public static final String ERROR_OF_CARD_ID = "error of card id: %d";

	private final CardDao cardDao;

	@Transactional
	public CardDto.Redirection create(CardDto.WriteRequest request) {
		Card card = request.toEntity();
		Card cardInfo = cardDao.save(card);
		return new CardDto.Redirection(cardInfo.getCardId());
	}

	@Transactional(readOnly = true)
	public CardDto.CardResponse readFrom(Long id) {
		Card cardInfo = cardDao.findById(id)
			.orElseThrow(() -> new IllegalArgumentException(ERROR_MESSAGE.apply(ERROR_OF_CARD_ID, id)));
		return new CardDto.CardResponse(cardInfo);
	}

	@Transactional(readOnly = true)
	public CardDto.CardsResponse readAllFrom(Long userId) {
		Map<Card.TodoStatus, List<Card>> cardsInfo = getCardsInfo(userId);
		Map<Card.TodoStatus, Long> numberOfStatusInfo =  getNumberOfCardStatusInfo(userId);

		List<CardByStatus> cards = cardByStatusMapper(cardsInfo, numberOfStatusInfo);
		return new CardDto.CardsResponse(cards);
	}

	public void updateOf(Long cardId, CardDto.EditRequest request) {
		Card card = getCard(cardId);
		card.modify(request.getSubject(), request.getContent());
		cardDao.save(card);
	}

	public void deleteFrom(Long cardId) {
		Card cardInfo = getCard(cardId);
		cardDao.delete(cardInfo.getCardId());
	}

	@Transactional
	public void moveCardTo(Long cardId, Card.TodoStatus toStatus, Long toOrder) {
		Card cardInfo = getCard(cardId);

		if (cardInfo.isPositionedAt(toStatus, toOrder)) {
			return;
		}

		cardDao.updatePosition(cardInfo, toStatus, toOrder);
	}

	private List<CardByStatus> cardByStatusMapper(Map<Card.TodoStatus, List<Card>> cardsInfo,
		Map<Card.TodoStatus, Long> numberOfStatus) {
		return cardsInfo.keySet().stream()
			.map(status -> new CardByStatus(status, numberOfStatus.get(status), cardsInfo.get(status)))
			.collect(Collectors.toList());
	}

	private Map<Card.TodoStatus, List<Card>> getCardsInfo(Long userId) {
		return Arrays.stream(Card.TodoStatus.values())
			.collect(Collectors.toMap(
				key -> key,
				status -> cardDao.findByUserIdAndTodoStatus(userId, status))
			);
	}

	private Map<Card.TodoStatus, Long> getNumberOfCardStatusInfo(Long userId) {
		List<CardStatusNumber> numberOfStatus = cardDao.findGroupByTodoStatus(userId);
		return numberOfStatus.stream()
			.collect(Collectors.toMap(CardStatusNumber::getStatus, CardStatusNumber::getNumberOfStatus));
	}

	// todo custom exception
	private Card getCard(Long cardId) {
		return cardDao.findById(cardId)
			.orElseThrow(() -> new IllegalArgumentException(ERROR_MESSAGE.apply(ERROR_OF_CARD_ID, cardId)));
	}
}
