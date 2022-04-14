package todolist.dto.card;

import java.util.List;
import java.util.Map;

public class ResponseCardsDto {

    private Map<String, List<ResponseCardDto>> cards;

    public ResponseCardsDto(Map<String, List<ResponseCardDto>> cards) {
        this.cards = cards;
    }

    public Map<String, List<ResponseCardDto>> getCards() {
        return cards;
    }

}
