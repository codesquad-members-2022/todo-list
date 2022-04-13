package team03.todoapp.Service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import team03.todoapp.controller.dto.CardAddFormRequest;
import team03.todoapp.controller.dto.CardMoveFormRequest;
import team03.todoapp.controller.dto.CardResponse;
import team03.todoapp.controller.dto.CardUpdateFormRequest;
import team03.todoapp.controller.dto.CardsResponse;
import team03.todoapp.repository.CardRepository;
import team03.todoapp.repository.domain.Card;

@Service
public class CardService {

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public void remove(Long cardId) {
        cardRepository.delete(cardId);
    }

    public Long add(CardAddFormRequest cardAddFormRequest) {
        Card card = cardAddFormRequest.toEntity();
        return cardRepository.insert(card);
    }

    public void move(Long cardId, CardMoveFormRequest cardMoveFormRequest) {
        cardRepository.updateLocation(cardId, cardMoveFormRequest);
    }

    public void update(Long cardId, CardUpdateFormRequest request) {
        Card card = cardRepository.findById(cardId)
            .orElseThrow(() -> new IllegalArgumentException("카드를 찾을 수 없습니다."));

        card.update(request.getTitle(), request.getContent());
        cardRepository.update(card);
    }

    public Card findOne(Long cardId) {
        Card card = cardRepository.findById(cardId)
            .orElseThrow(() -> new IllegalArgumentException("카드를 찾을 수 없습니다."));
        return card;
    }

    public CardsResponse findAll() {
        List<Card> cards = cardRepository.findAll();

        return createCardsResponse(cards);
    }

    private CardsResponse createCardsResponse(List<Card> cards) {
        CardsResponse cardsResponse = new CardsResponse();

        List<CardResponse> todos = new ArrayList<>();
        List<CardResponse> ings = new ArrayList<>();
        List<CardResponse> dones = new ArrayList<>();

        for (Card card : cards) {
            if (card.getCurrentLocation().equals("todo")) {
                todos.add(new CardResponse(card));
            } else if (card.getCurrentLocation().equals("ing")) {
                ings.add(new CardResponse(card));
            } else if (card.getCurrentLocation().equals("done")) {
                dones.add(new CardResponse(card));
            }
        }

        cardsResponse.putCards("todo", todos);
        cardsResponse.putCards("ing", ings);
        cardsResponse.putCards("done", dones);

        return cardsResponse;
    }

}
