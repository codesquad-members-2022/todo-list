package team03.todoapp.Service;

import org.springframework.stereotype.Service;
import team03.todoapp.controller.dto.CardAddFormRequest;
import team03.todoapp.controller.dto.CardMoveFormRequest;
import team03.todoapp.domain.Card;
import team03.todoapp.repository.CardRepository;

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
        // todo: card 정보 넘기기
        Card card = cardAddFormRequest.toCardEntity();
        return cardRepository.insert(card);
    }

    public void move(Long cardId, CardMoveFormRequest cardMoveFormRequest) {
        cardRepository.updateLocation(cardId, cardMoveFormRequest);
    }
}
