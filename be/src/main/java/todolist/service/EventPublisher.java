package todolist.service;

import todolist.domain.event.Action;
import todolist.domain.event.Event;
import todolist.dto.card.ResponseCardDto;

import java.util.function.Function;

public class EventPublisher {
    static Function<ResponseCardDto,Event> add = (dto -> new Event(dto.getTitle(), dto.getSection(), Action.ADD));
    static Function<ResponseCardDto,Event> update = (dto -> new Event(dto.getTitle(), dto.getSection(), Action.UPDATE));
    static Function<ResponseCardDto,Event> move = (dto -> new Event(dto.getTitle(), dto.getSection(), Action.MOVE));
    static Function<ResponseCardDto,Event> remove = (dto -> new Event(dto.getTitle(), dto.getSection(), Action.REMOVE));
}
