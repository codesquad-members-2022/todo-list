package team03.todoapp.controller.dto;

import javax.validation.constraints.NotNull;
import team03.todoapp.controller.CardLocation;

public class CardMoveFormRequest {

    @NotNull
    private CardLocation destinationLocation;
    private Long prevItemId;
    private Long nextItemId;

    public CardMoveFormRequest() {
    }

    public CardMoveFormRequest(CardLocation destinationLocation, Long prevItemId, Long nextItemId) {
        this.destinationLocation = destinationLocation;
        this.prevItemId = prevItemId;
        this.nextItemId = nextItemId;
    }

    public void setDestinationLocation(CardLocation destinationLocation) {
        this.destinationLocation = destinationLocation;
    }

    public void setPrevItemId(Long prevItemId) {
        this.prevItemId = prevItemId;
    }

    public void setNextItemId(Long nextItemId) {
        this.nextItemId = nextItemId;
    }

    public CardLocation getDestinationLocation() {
        return destinationLocation;
    }

    public Long getPrevItemId() {
        return prevItemId;
    }

    public Long getNextItemId() {
        return nextItemId;
    }

}
