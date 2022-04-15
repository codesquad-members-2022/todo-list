package kr.codesquad.todolist.service;

import kr.codesquad.todolist.controller.request.CardRequestDto;
import kr.codesquad.todolist.controller.response.CardResponseDto;
import kr.codesquad.todolist.dao.CardDao;
import kr.codesquad.todolist.dao.HistoryDao;
import kr.codesquad.todolist.domain.Card;
import kr.codesquad.todolist.domain.History;
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
	private final HistoryDao historyDao;

	@Transactional
	public CardResponseDto.Redirection create(CardRequestDto.WriteRequest request) {
		Card card = request.toEntity();
		Card cardInfo = cardDao.save(card);
		historyDao.save(History.buildWith(History.Action.CREATE, cardInfo).build());
		return new CardResponseDto.Redirection(cardInfo.getCardId());
	}

	@Transactional(readOnly = true)
	public CardResponseDto.CardResponse readFrom(Long id) {
		Card cardInfo = cardDao.findById(id)
			.orElseThrow(() -> new IllegalArgumentException(ERROR_MESSAGE.apply(ERROR_OF_CARD_ID, id)));
		return new CardResponseDto.CardResponse(cardInfo);
	}

	@Transactional(readOnly = true)
	public CardResponseDto.CardsResponse readAllFrom(Long userId) {
		Map<Card.TodoStatus, List<Card>> cardsInfo = getCardsInfo(userId);
		Map<Card.TodoStatus, Long> numberOfStatusInfo =  getNumberOfCardStatusInfo(userId);

		return new CardResponseDto.CardsResponse(cardsInfo, numberOfStatusInfo);
	}

	public void updateOf(Long cardId, CardRequestDto.EditRequest request) {
		Card card = getCard(cardId);
		card.modify(request.getSubject(), request.getContent());
		cardDao.save(card);
		historyDao.save(History.buildWith(History.Action.UPDATE, card).build());
	}

	public void deleteFrom(Long cardId) {
		Card cardInfo = getCard(cardId);
		cardDao.delete(cardInfo.getCardId());
		historyDao.save(History.buildWith(History.Action.DELETE, cardInfo).build());
	}

	@Transactional
	public void moveCardTo(Long cardId, Card.TodoStatus toStatus, Long toOrder) {
		Card cardInfo = getCard(cardId);

		if (cardInfo.isPositionedAt(toStatus, toOrder)) {
			return;
		}

		cardDao.updatePosition(cardInfo, toStatus, toOrder);

		if (!toStatus.equals(cardInfo.getStatus())) {
			History history = History.buildWith(History.Action.MOVE, cardInfo)
					.currentCardStatus(toStatus)
					.build();
			historyDao.save(history);
		}
	}

	/**
	 * 사용자의 status 별 카드 목록
	 * @param userId
	 * @return
	 */
	private Map<Card.TodoStatus, List<Card>> getCardsInfo(Long userId) {
		return Arrays.stream(Card.TodoStatus.values())
			.collect(Collectors.toMap(
				key -> key,
				status -> cardDao.findByUserIdAndTodoStatus(userId, status))
			);
	}

	/**
	 * 사용자의 status 별 카드 개수
	 * @param userId
	 * @return
	 */
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
