package team03.todoapp.controller.dto;

public class CardAddIdResponse {
    private Long cardId;

    public CardAddIdResponse(Long cardId) {
        this.cardId = cardId;
    }

    public Long getCardId() {
        return cardId;
    }

}
