package todolist.domain.event;

import todolist.dto.event.RequestEventDto;

import java.util.function.BiFunction;

public enum Action {
    ADD((dto, action) -> new Event(dto, action)),
    MOVE((dto, action) -> new Event(dto, action)),
    UPDATE((dto, action) -> new Event(dto, action)),
    REMOVE((dto, action) -> new Event(dto, action));


    private BiFunction<RequestEventDto, Action, Event> eventFunction;

    Action(BiFunction<RequestEventDto, Action, Event> eventFunction) {
        this.eventFunction = eventFunction;
    }

    public Event publish(RequestEventDto requestEventDto) {
        return eventFunction.apply(requestEventDto, this);
    }
}
