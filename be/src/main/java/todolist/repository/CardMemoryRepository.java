package todolist.repository;

import org.springframework.stereotype.Repository;
import todolist.domain.Card;

import java.util.List;
import java.util.Optional;

@Repository
public class CardMemoryRepository implements TodoRepository<Card> {

    @Override
    public Card save() {
        return null;
    }

    @Override
    public Optional<Card> findById() {
        return Optional.empty();
    }

    @Override
    public List<Card> findAll() {
        return null;
    }

    @Override
    public void delete() {

    }
}
