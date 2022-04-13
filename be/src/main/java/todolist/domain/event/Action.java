package todolist.domain.event;

import todolist.dto.event.RequestEventDto;

import java.util.function.BiFunction;

public enum Action {
    ADD((dto, action) -> new Event(dto.getTitle(), dto.getCurrentSection(), action)),
    MOVE((dto, action) -> new Event(dto.getTitle(),dto.getPrevSection(), dto.getCurrentSection(), action)),
    UPDATE((dto, action) -> new Event(dto.getTitle(), dto.getCurrentSection(), action)),
    REMOVE((dto, action) -> new Event(dto.getTitle(), dto.getCurrentSection(), action));


    private BiFunction<RequestEventDto, Action, Event> eventFunction;

    Action(BiFunction<RequestEventDto, Action, Event> eventFunction) {
        this.eventFunction = eventFunction;
    }

    public Event publish(RequestEventDto requestEventDto) {
        return eventFunction.apply(requestEventDto, this);
    }
}
