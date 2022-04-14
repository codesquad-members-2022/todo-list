package team03.todoapp.controller.dto;

import javax.validation.constraints.NotNull;

public class CardMoveFormRequest {

    @NotNull
    private String destinationLocation;
    private Long prevItemId;
    private Long nextItemId;

    public CardMoveFormRequest() {
    }

    public CardMoveFormRequest(String destinationLocation, Long prevItemId, Long nextItemId) {
        this.destinationLocation = destinationLocation;
        this.prevItemId = prevItemId;
        this.nextItemId = nextItemId;
    }

    public void setDestinationLocation(String destinationLocation) {
        this.destinationLocation = destinationLocation;
    }

    public void setPrevItemId(Long prevItemId) {
        this.prevItemId = prevItemId;
    }

    public void setNextItemId(Long nextItemId) {
        this.nextItemId = nextItemId;
    }

    public String getDestinationLocation() {
        return destinationLocation;
    }

    public Long getPrevItemId() {
        return prevItemId;
    }

    public Long getNextItemId() {
        return nextItemId;
    }

}
