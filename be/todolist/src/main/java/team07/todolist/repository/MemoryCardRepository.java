package team07.todolist.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import team07.todolist.domain.Card;
import team07.todolist.dto.RequestCard;

public class MemoryCardRepository implements CardRepository {

	private static final Map<Long, Card> store = new ConcurrentHashMap<>();

	private static AtomicLong sequence = new AtomicLong();

	@Override
	public Card findById(Long id) {
		return store.get(id);
	}

	@Override
	public Card save(Card card) {
		Long id = sequence.incrementAndGet();

		Card cardWithId = new Card(card, id);

		store.put(id, cardWithId);
		return cardWithId;
	}

	@Override
	public Card delete(Long id) {
		Card card = store.get(id);
		card.delete();

		int row = card.getSequence();
		int status = card.getStatus();

		store.values().stream()
			.filter(c -> c.isSameStatus(status))
			.filter(c -> c.overRow(row))
			.forEach(Card::decreaseRow);

		return card;
	}

	@Override
	public Card updateStatusAndRow(Long id, RequestCard requestCard) {
		Card originCard = store.get(id);
		int originRow = originCard.getSequence();
		int originStatus = originCard.getStatus();

		int row = requestCard.getSequence();
		int status = requestCard.getStatus();

		store.values().stream()
			.filter(c -> c.isSameStatus(originStatus))
			.filter(c -> c.overRow(originRow))
			.forEach(Card::decreaseRow);

		store.values().stream()
			.filter(c -> c.isSameStatus(status))
			.filter(c -> c.andOverRow(row))
			.forEach(Card::increaseRow);

		Card updateCard = new Card(originCard, id, row, status);

		store.put(id, updateCard);

		return updateCard;
	}

	@Override
	public Card updateText(Long id, Card card) {
		Card newCard = new Card(card, id);
		store.put(id, newCard);
		return newCard;
	}

	@Override
	public List<Card> findAll() {
		return new ArrayList<>(store.values());
	}

}
