package todolist.domain.event;

import todolist.dto.event.EventDto;

import java.util.function.BiFunction;

public enum Action {
    ADD((dto, action) -> new Event(dto.getTitle(), dto.getCurrentSection(), action)),
    MOVE((dto, action) -> new Event(dto.getTitle(),dto.getPrevSection(), dto.getCurrentSection(), action)),
    UPDATE((dto, action) -> new Event(dto.getTitle(), dto.getCurrentSection(), action)),
    REMOVE((dto, action) -> new Event(dto.getTitle(), dto.getCurrentSection(), action));


    private BiFunction<EventDto, Action, Event> eventFunction;

    Action(BiFunction<EventDto, Action, Event> eventFunction) {
        this.eventFunction = eventFunction;
    }

    public Event publish(EventDto eventDto) {
        return eventFunction.apply(eventDto, this);
    }
}
