package todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import todolist.domain.event.Action;
import todolist.domain.event.Event;
import todolist.dto.event.EventDto;
import todolist.repository.EventMemoryRepository;

import java.util.List;

@Service
public class EventService {

    private EventMemoryRepository repository;

    @Autowired
    public EventService(EventMemoryRepository repository) {
        this.repository = repository;
    }

    public void addEvent(EventDto eventDto, Action action) {
        Event event = action.publish(eventDto);
        repository.save(event);
    }

    public List<Event> getEventList() {
        return repository.findAll();
    }

}
