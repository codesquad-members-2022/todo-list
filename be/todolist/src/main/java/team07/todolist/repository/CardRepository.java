package team07.todolist.repository;

import java.util.List;
import team07.todolist.domain.Card;

public interface CardRepository {

  void save(Card card, int status);

  //void delete(int row, int status);
  void delete(Long id);

  Card update(Card card);

  List<Card> findAll();

}
