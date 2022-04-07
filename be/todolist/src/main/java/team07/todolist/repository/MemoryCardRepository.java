package team07.todolist.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;
import team07.todolist.domain.Card;

@Repository
public class MemoryCardRepository implements CardRepository {

	private static final Map<Long, Card> store = new ConcurrentHashMap<>();
	private static AtomicLong toDoRow = new AtomicLong();
	private static AtomicLong progressRow = new AtomicLong();
	private static AtomicLong doneRow = new AtomicLong();
	private static AtomicLong sequence = new AtomicLong();

	@Override
	public Card findById(Long id) {
		return store.get(id);
	}

	@Override
	public void save(Card card, int status) {
		// 해당하는 Row의 값을 증가
		switch (status) {
			case 1:
				toDoRow.incrementAndGet();
				break;
			case 2:
				progressRow.incrementAndGet();
				break;
			case 3:
				doneRow.incrementAndGet();
				break;
		}

		// new id 산정
		Long id = sequence.incrementAndGet();

		// Card 등록
		Card cardWithId = new Card.Builder(card).id(id).build();

		store.put(id, cardWithId);
	}

	@Override
	public Card delete(Long id) {
		Card card = store.get(id);
		Integer row = card.getRow();
		Integer status = card.getStatus();

		switch (status) {
			case 1:
				toDoRow.decrementAndGet();
				break;
			case 2:
				progressRow.decrementAndGet();
				break;
			case 3:
				doneRow.decrementAndGet();
				break;
		}

		card.delete();

		store.values().stream()
			.filter(c -> c.getStatus().equals(status))
			.filter(c -> c.getRow() > row)
			.forEach(Card::decreaseRow);

		return card;
	}

	@Override
	public Card updateStatusAndRow(Long id, Integer row, Integer status) {
		//todo
		// status가 다름
		// 이전에 있던 status에서 id의 카드보다 row값이 큰 카드들의 row값들을 -1 해주고
		// 바뀐 status에서 id의 카드보다 row값이 큰 카드들의 row값을 +1 해준다.

		Card originCard = store.get(id);
		Integer originRow = originCard.getRow();
		Integer originStatus = originCard.getStatus();

		store.values().stream()
			.filter(c -> c.getStatus().equals(originStatus))
			.filter(c -> c.getRow() > originRow)
			.forEach(Card::decreaseRow);

		store.values().stream()
			.filter(c -> c.getStatus().equals(status))
			.filter(c -> c.getRow() >= row)
			.forEach(Card::increaseRow);

		//todo originCard에서 row, status 매개변수 값으로 변경 후 return
		Card updateCard = new Card.Builder(originCard).id(id).row(row).status(status).build();

		store.put(id, updateCard);

		return updateCard;
	}

	@Override
	public Card updateRow(Long id, Card card) {

		// 기존 열의 카드
		Card oldCard = store.get(id);
		// 바뀐 열의 카드
		Card newCard = card;
		int oldRow = oldCard.getRow();
		int newRow = newCard.getRow();
		boolean isHeight = oldRow < newRow;

		if (isHeight) {
			store.values().stream()
				.filter(c -> c.getRow() > oldRow && c.getRow() <= newRow)
				.forEach(Card::decreaseRow);
		} else {
			store.values().stream()
				.filter(c -> c.getRow() < oldRow && c.getRow() >= newRow)
				.forEach(Card::increaseRow);
		}
		newCard = new Card.Builder(newCard).id(id).build();
		store.put(id, newCard);

		return newCard;
	}

	@Override
	public Card updateText(Long id, Card card) {
		card.setId(id);
		store.put(id, card);
		return card;
	}

	@Override
	public List<Card> findAll() {
		return new ArrayList<>(store.values());
	}

}
