package team03.todoapp.controller.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardsResponse {

    private Map<String, List<CardResponse>> cardsClassifiedByLocation = new HashMap<>();

    public void putCards(String Location, List<CardResponse> cards) {
        cardsClassifiedByLocation.put(Location, cards);
    }

    public Map<String, List<CardResponse>> getCardsClassifiedByLocation() {
        return cardsClassifiedByLocation;
    }
}
