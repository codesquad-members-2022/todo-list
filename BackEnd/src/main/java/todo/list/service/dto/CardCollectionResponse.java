package todo.list.service.dto;

import todo.list.domain.Card;

import java.util.List;
import java.util.stream.Collectors;

public class CardCollectionResponse {

    private List<CardQueryResponse> todoCollection;
    private List<CardQueryResponse> inProgressCollection;
    private List<CardQueryResponse> doneCollection;

    public CardCollectionResponse(List<Card> todoCollection, List<Card> inProgressCollection, List<Card> doneCollection) {
        this.todoCollection = makeCollection(todoCollection);
        this.inProgressCollection = makeCollection(inProgressCollection);
        this.doneCollection = makeCollection(doneCollection);
    }

    private List<CardQueryResponse> makeCollection(List<Card> cards) {
        return cards.stream()
                .map(CardQueryResponse::new)
                .collect(Collectors.toList());
    }

    public List<CardQueryResponse> getTodoCollection() {
        return todoCollection;
    }

    public List<CardQueryResponse> getInProgressCollection() {
        return inProgressCollection;
    }

    public List<CardQueryResponse> getDoneCollection() {
        return doneCollection;
    }
}
