package todolist.repository;

import todolist.domain.event.Event;

import java.util.List;

public interface EventRepository {

    void save(Event event);

    List<Event> findAll();
}
