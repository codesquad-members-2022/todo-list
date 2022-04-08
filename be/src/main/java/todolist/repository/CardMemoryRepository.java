package todolist.repository;

import org.springframework.stereotype.Repository;
import todolist.domain.Card;
import todolist.dto.RequestCardDto;

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
    public Card findById(Long id) {
        return store.stream().filter(card -> card.getId() == id).findAny().orElseThrow(()-> new IllegalArgumentException());
    }

    @Override
    public List<Card> findAll() {
        return Collections.unmodifiableList(store);
    }

    @Override
    public void update(Card updatedCard) {
        //쿼리에 updatedCard.getId, updatedCard.getContent....
    }

    @Override
    public void delete(Long id) {
        Card card = findById(id);
        store.remove(card);
    }


}
