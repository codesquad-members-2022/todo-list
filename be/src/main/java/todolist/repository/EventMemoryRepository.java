package todolist.repository;

import org.springframework.stereotype.Repository;
import todolist.domain.event.Event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class EventMemoryRepository {

    private static List<Event> store = new ArrayList<>();
    private static AtomicLong id = new AtomicLong();

    public void save(Event event) {
        event.setId(id.incrementAndGet());
        store.add(event);
    }

    public List<Event> findAll() {
        return Collections.unmodifiableList(store);
    }
}
