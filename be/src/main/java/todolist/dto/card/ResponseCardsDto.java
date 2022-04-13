package todolist.dto.card;

import lombok.Getter;
import todolist.domain.card.Card;

import java.util.*;

@Getter
public class ResponseCardsDto {

    private Map<String, List<ResponseCardDto>> cards;

    public ResponseCardsDto() {
        cards = new HashMap<>();
        cards.put("해야할 일", new ArrayList<>());
        cards.put("하고 있는 일", new ArrayList<>());
        cards.put("완료된 일", new ArrayList<>());
    }

    public void categorizeCards(List<Card> cardlist) {
        for (Card card : cardlist) {
            String section = card.getSection();
            List<ResponseCardDto> responseSection = cards.get(section);
            responseSection.add(card.toResponseCardDto());
        }
    }
}
