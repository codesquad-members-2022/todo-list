package todolist.repository;

import org.springframework.stereotype.Repository;
import todolist.domain.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class CardMemoryRepository implements TodoRepository<Card> {

    private static List<Card> store = new ArrayList<>();
    private static AtomicLong id = new AtomicLong();

    @Override
    public Card save(Card card) {
        card.setId(id.incrementAndGet());
        store.add(card);
        return card;
    }

    @Override
    public Optional<Card> findById(Long id) {
        return store.stream().filter(card -> card.getId() == id).findAny();
    }

    @Override
    public List<Card> findAll() {
        return Collections.unmodifiableList(store);
    }

    @Override
    public void delete(Long id) {
        store.remove(id);
    }
}
