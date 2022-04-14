package todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import todolist.domain.event.Action;
import todolist.domain.event.Event;
import todolist.dto.event.RequestEventDto;
import todolist.repository.EventRepository;

import java.util.List;

@Service
public class EventService {

    private EventRepository repository;

    @Autowired
    public EventService(EventRepository repository) {
        this.repository = repository;
    }

    public void addEvent(RequestEventDto requestEventDto, Action action) {
        Event event = action.publish(requestEventDto);
        repository.save(event);
    }

    public List<Event> getEventList() {
        return repository.findAll();
    }

}
