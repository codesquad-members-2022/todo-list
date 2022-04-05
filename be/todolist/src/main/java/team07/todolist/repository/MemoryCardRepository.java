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
		card.setId(id);
		store.put(id, card);
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
	public Card update(Card card) {

		return null;
	}

	@Override
	public List<Card> findAll() {
		return new ArrayList<>(store.values());
	}

}
