package team07.todolist.repository;

import java.util.List;
import team07.todolist.domain.Card;

public interface CardRepository {

	Card findById(Long id);

	void save(Card card, int status);

	Card delete(Long id);

	Card updateStatusAndRow(Long id, Integer row, Integer status);

	Card updateRow(Long id, Card card);

	Card updateText(Long id, Card card);

	List<Card> findAll();

}
