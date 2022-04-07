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
	public Card updateStatusAndRow(Long id, Integer status) {
		//todo
		// status가 다름
		// 이전에 있던 status에서 id의 카드보다 row값이 큰 카드들의 row값들을 -1 해주고
		// 바뀐 status에서 id의 카드보다 row값이 큰 카드들의 row값을 +1 해준다.

		return null;
	}

	@Override
	public Card updateRow(Long id, Card card) {
		//todo
		// status는 동일하고 row만 변함
		// 변한 row를 기반으로 row값이 사이에 들어 있는 Card들은 row값을 -1 해준다.
		//card.setId(id);
		//Integer row = card.getRow();

		// 결국 높은쪽에서 낮은 쪽으로 가면, 그 사이 값들은 ++가 되고
		//     낮은쪽에서 높은 쪽으로 가면, 그 사이 값들은 --가 된다.
//		1
//		2
//		3 -> 4
//		4 -> 5
//		5 -> 6
//		6 -> 7
//		7 -> 3
		//3-> 6
		//기존 4~6이였던 것들은 마이너스
		//6-> 3
		//기존 3~5는 플러스가 된다

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
		newCard.setId(id);
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
