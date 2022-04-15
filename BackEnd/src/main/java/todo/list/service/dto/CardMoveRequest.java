package todo.list.service.dto;

import todo.list.domain.CardStatus;

public class CardMoveRequest {
    private CardStatus beforeStatus;
    private CardStatus nowStatus;

    public CardStatus getBeforeStatus() {
        return beforeStatus;
    }

    public CardStatus getNowStatus() {
        return nowStatus;
    }
}
