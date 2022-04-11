package team07.todolist.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;
import team07.todolist.domain.Card;
import team07.todolist.dto.RequestCard;

@Repository
public class MemoryCardRepository implements CardRepository {

	private static final Map<Long, Card> store = new ConcurrentHashMap<>();

	private static AtomicLong sequence = new AtomicLong();

	@Override
	public Card findById(Long id) {
		return store.get(id);
	}

	@Override
	public void save(Card card, int status) {
		Long id = sequence.incrementAndGet();

		Card cardWithId = new Card(card, id);

		store.put(id, cardWithId);
	}

	@Override
	public Card delete(Long id) {
		Card card = store.get(id);
		Integer row = card.getRow();
		Integer status = card.getStatus();

		card.delete();

		store.values().stream()
			.filter(c -> c.getStatus().equals(status))
			.filter(c -> c.getRow() > row)
			.forEach(Card::decreaseRow);

		return card;
	}

	@Override
	public Card updateStatusAndRow(Long id, RequestCard requestCard) {
		Card originCard = store.get(id);
		Integer originRow = originCard.getRow();
		Integer originStatus = originCard.getStatus();

		Integer row = requestCard.getRow();
		Integer status = requestCard.getStatus();

		store.values().stream()
			.filter(c -> c.getStatus().equals(originStatus))
			.filter(c -> c.getRow() > originRow)
			.forEach(Card::decreaseRow);

		store.values().stream()
			.filter(c -> c.getStatus().equals(status))
			.filter(c -> c.getRow() >= row)
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
