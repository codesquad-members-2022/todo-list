package todo.list.service;

import java.util.List;

public class CardCollectionDto {

    private int count;
    private List<CardDto> cards;

    public CardCollectionDto(List<CardDto> cards) {
        this.count = cards.size();
        this.cards = cards;
    }

    public int getCount() {
        return count;
    }

    public List<CardDto> getCards() {
        return cards;
    }
}
