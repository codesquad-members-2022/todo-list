package todolist.dto.card;

import todolist.domain.card.Card;

import java.util.List;
import java.util.Map;

public class ResponseCardsDto {

    private Map<String, List<Card>> cards;

    public ResponseCardsDto(Map<String, List<Card>> cards) {
        this.cards = cards;
    }

    public Map<String, List<Card>> getCards() {
        return cards;
    }

}
