package todo.list.service.dto;

import todo.list.domain.Card;

import java.util.List;
import java.util.stream.Collectors;

public class CardCollectionResponse {

    private List<CardDto> todoCollection;
    private List<CardDto> inProgressCollection;
    private List<CardDto> doneCollection;

    public CardCollectionResponse(List<Card> todoCollection, List<Card> inProgressCollection, List<Card> doneCollection) {
        this.todoCollection = makeCollection(todoCollection);
        this.inProgressCollection = makeCollection(inProgressCollection);
        this.doneCollection = makeCollection(doneCollection);
    }

    private List<CardDto> makeCollection(List<Card> cards) {
        return cards.stream()
                .map(CardDto::new)
                .collect(Collectors.toList());
    }

    public List<CardDto> getTodoCollection() {
        return todoCollection;
    }

    public List<CardDto> getInProgressCollection() {
        return inProgressCollection;
    }

    public List<CardDto> getDoneCollection() {
        return doneCollection;
    }
}
