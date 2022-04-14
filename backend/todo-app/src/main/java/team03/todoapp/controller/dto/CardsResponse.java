package team03.todoapp.controller.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import team03.todoapp.controller.CardLocation;
import team03.todoapp.repository.domain.Card;

public class CardsResponse {

    private Map<CardLocation, List<CardResponse>> cardsClassifiedByLocation = new HashMap<>();

    public CardsResponse(List<Card> cards) {
        List<CardResponse> todos = new ArrayList<>();
        List<CardResponse> ings = new ArrayList<>();
        List<CardResponse> dones = new ArrayList<>();

        for (Card card : cards) {
            classifyCard(todos, ings, dones, card);
        }

        cardsClassifiedByLocation.put(CardLocation.todo, todos);
        cardsClassifiedByLocation.put(CardLocation.ing, ings);
        cardsClassifiedByLocation.put(CardLocation.done, dones);
    }

    private void classifyCard(List<CardResponse> todos, List<CardResponse> ings,
        List<CardResponse> dones, Card card) {
        switch (card.getCurrentLocation()) {
            case todo:
                todos.add(new CardResponse(card));
                break;
            case ing:
                ings.add(new CardResponse(card));
                break;
            case done:
                dones.add(new CardResponse(card));
                break;
        }
    }

    public Map<CardLocation, List<CardResponse>> getCardsClassifiedByLocation() {
        return cardsClassifiedByLocation;
    }

}
