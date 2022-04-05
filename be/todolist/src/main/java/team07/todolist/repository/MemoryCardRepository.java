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
	private static AtomicLong toDoRow;
	private static AtomicLong progressRow;
	private static AtomicLong doneRow;

	@Override
	public void save(Card card) {
		//todo

	}

	@Override
	public void delete(int row, int status) {

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
