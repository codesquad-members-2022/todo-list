package team07.todolist.repository;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import team07.todolist.domain.Card;
import team07.todolist.dto.RequestCard;

public interface CardRepository {

	Card findById(Long id);

	Card save(Card card);

	Card delete(Long id);

	Card updateStatusAndRow(Long id, RequestCard requestCard);

	Card updateText(Long id, Card card);

	List<Card> findAll();

}
