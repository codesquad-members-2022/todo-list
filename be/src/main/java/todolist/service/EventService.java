package todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import todolist.domain.event.Action;
import todolist.domain.event.Event;
import todolist.dto.card.ResponseCardDto;
import todolist.repository.EventMemoryRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
public class EventService {

    private EventMemoryRepository repository;
    private Map<Action, Function<ResponseCardDto,Event>> eventHandlingMethods = new HashMap<>();

    @Autowired
    public EventService(EventMemoryRepository repository) {
        this.repository = repository;
        initEventMethods();
    }

    public void addEvent(ResponseCardDto responseCardDto, Action action) {
        Function<ResponseCardDto, Event> eventFunction = eventHandlingMethods.get(action);
        Event event = eventFunction.apply(responseCardDto);
        repository.save(event);
    }

    public List<Event> getEventList() {
        return repository.findAll();
    }

    private void initEventMethods() {
        eventHandlingMethods.put(Action.ADD, EventPublisher.add);
        eventHandlingMethods.put(Action.UPDATE, EventPublisher.update);
        eventHandlingMethods.put(Action.MOVE, EventPublisher.move);
        eventHandlingMethods.put(Action.REMOVE, EventPublisher.remove);
    }
}
