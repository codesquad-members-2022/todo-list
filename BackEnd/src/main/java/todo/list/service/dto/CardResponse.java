package todo.list.service.dto;

public class CardResponse {

    private CardCollectionDto todoCollection;
    private CardCollectionDto inProgressCollection;
    private CardCollectionDto doneCollection;

    public CardResponse(CardCollectionDto todoCollection, CardCollectionDto inProgressCollection, CardCollectionDto doneCollection) {
        this.todoCollection = todoCollection;
        this.inProgressCollection = inProgressCollection;
        this.doneCollection = doneCollection;
    }

    public CardCollectionDto getTodoCollection() {
        return todoCollection;
    }

    public CardCollectionDto getInProgressCollection() {
        return inProgressCollection;
    }

    public CardCollectionDto getDoneCollection() {
        return doneCollection;
    }
}
