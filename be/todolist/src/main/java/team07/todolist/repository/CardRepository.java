package team07.todolist.repository;

import java.util.List;
import team07.todolist.domain.Card;

public interface CardRepository {

	void save(Card card);

	void delete(int row, int status);

	Card update(Card card);

	List<Card> findAll();

}
