package todolist.dto.card;

import todolist.domain.card.Card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseCardsDto {

    private Map<String, List<ResponseCardDto>> cards;

    public ResponseCardsDto() {
        cards = new HashMap<>();
        cards.put("해야할 일", new ArrayList<>());
        cards.put("하고 있는 일", new ArrayList<>());
        cards.put("완료된 일", new ArrayList<>());
    }

    public Map<String, List<ResponseCardDto>> getCards() {
        return cards;
    }

    public void categorizeCards(List<Card> cardlist) {
        for (Card card : cardlist) {
            String section = card.getSection();
            List<ResponseCardDto> responseSection = cards.get(section);
            responseSection.add(card.toResponseCardDto());
        }
    }
}
