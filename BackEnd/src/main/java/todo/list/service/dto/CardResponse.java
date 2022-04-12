package todo.list.service.dto;

import java.util.List;

public class CardResponse {

    private List<CardDto> todoCollection;
    private List<CardDto> inProgressCollection;
    private List<CardDto> doneCollection;

    public CardResponse(List<CardDto> todoCollection, List<CardDto> inProgressCollection, List<CardDto> doneCollection) {
        this.todoCollection = todoCollection;
        this.inProgressCollection = inProgressCollection;
        this.doneCollection = doneCollection;
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
