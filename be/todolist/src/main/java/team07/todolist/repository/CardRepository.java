package team07.todolist.repository;

import java.util.List;
import team07.todolist.domain.Card;
import team07.todolist.dto.RequestCard;

public interface CardRepository {

	Card findById(Long id);

	void save(Card card, int status);

	Card delete(Long id);

	Card updateStatus(Long id);

	Card updateRow(Long id);

	Card updateText(Long id);

	List<Card> findAll();

}
