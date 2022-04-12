package kr.codesquad.todolist.card;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CardService {
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
		String errorMessage = String.format(ERROR_OF_CARD_ID, id);
		Card cardInfo = cardDao.findById(id)
			.orElseThrow(() -> new IllegalArgumentException(errorMessage));
		return new CardDto.CardResponse(cardInfo);
	}

	@Transactional(readOnly = true)
	public CardDto.CardsResponse readAllFrom(Long userId) {
		Map<Card.TodoStatus, List<Card>> cardsInfo = getCardsInfo(userId);
		Map<Card.TodoStatus, Long> numberOfStatusInfo =  getNumberOfCardStatusInfo(userId);

		List<CardByStatus> cards = toCardByStatuses(cardsInfo, numberOfStatusInfo);
		return new CardDto.CardsResponse(cards);
	}

	private List<CardByStatus> toCardByStatuses(Map<Card.TodoStatus, List<Card>> cardsInfo,
		Map<Card.TodoStatus, Long> numberOfStatus) {
		List<CardByStatus> cards = new ArrayList<>();
		cardsInfo.keySet().stream()
			.forEach(status -> cards.add(new CardByStatus(status, numberOfStatus.get(status), cardsInfo.get(status))));
		return cards;
	}

	private Map<Card.TodoStatus, List<Card>> getCardsInfo(Long userId) {
		Map<Card.TodoStatus, List<Card>> cardsInfo = new HashMap<>();
		Arrays.stream(Card.TodoStatus.values())
			.forEach(status -> cardsInfo.put(status, cardDao.findByUserIdAndTodoStatus(userId, status)));
		return cardsInfo;
	}

	private Map<Card.TodoStatus, Long> getNumberOfCardStatusInfo(Long userId) {
		List<CardStatusNumber> numberOfStatus = cardDao.findGroupByTodoStatus(userId);
		return numberOfStatus.stream()
			.collect(Collectors.toMap(CardStatusNumber::getStatus, CardStatusNumber::getNumberOfStatus));
	}
}
