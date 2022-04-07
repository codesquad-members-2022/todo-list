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

		Card updateCard = new Card.Builder(originCard).id(id).row(row).status(status).build();

		store.put(id, updateCard);

		return updateCard;
	}

	@Override
	public Card updateRow(Long id, Card card) {

		Card oldCard = store.get(id);
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

	@Override
	public void reset() {
		store.clear();

		store.put(1L, new Card.Builder().id(1L).userId("iOS").title("제목1").content("내용1").row(1).status(1).build());
		store.put(2L, new Card.Builder().id(2L).userId("iOS").title("제목2").content("내용2").row(2).status(1).build());
		store.put(3L, new Card.Builder().id(3L).userId("iOS").title("제목3").content("내용3").row(3).status(1).build());
		store.put(4L, new Card.Builder().id(4L).userId("iOS").title("제목4").content("내용4").row(1).status(2).build());
		store.put(5L, new Card.Builder().id(5L).userId("iOS").title("제목5").content("내용5").row(2).status(2).build());
		store.put(6L, new Card.Builder().id(6L).userId("iOS").title("제목6").content("내용6").row(3).status(2).build());
		store.put(7L, new Card.Builder().id(7L).userId("iOS").title("제목7").content("내용7").row(1).status(3).build());
		store.put(8L, new Card.Builder().id(8L).userId("iOS").title("제목8").content("내용8").row(2).status(3).build());
		store.put(9L, new Card.Builder().id(9L).userId("iOS").title("제목9").content("내용9").row(3).status(3).build());

		sequence.set(9L);
	}

}
