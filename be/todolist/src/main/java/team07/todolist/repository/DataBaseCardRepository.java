package team07.todolist.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import team07.todolist.domain.Card;
import team07.todolist.dto.RequestCard;

@Repository
public class DataBaseCardRepository implements CardRepository {

	@Override
	public Card findById(Long id) {
		return null;
	}

	@Override
	public Card save(Card card) {
		return null;
	}

	@Override
	public Card delete(Long id) {
		return null;
	}

	@Override
	public Card updateStatusAndRow(Long id, RequestCard requestCard) {
		return null;
	}

	@Override
	public Card updateText(Long id, Card card) {
		return null;
	}

	@Override
	public List<Card> findAll() {
		return null;
	}
}
