package todo.list.service;

import org.springframework.stereotype.Service;
import todo.list.service.dto.*;
import todo.list.domain.Card;
import todo.list.domain.CardStatus;
import todo.list.repository.CardRepository;

import java.util.List;

@Service
public class CardService {

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public CommandResultResponse save(CardSaveRequest cardSaveRequest) {
        Card card = cardSaveRequest.toEntity();
        Card savedCard = cardRepository.save(card);
        CardCommandResponse cardCommandResponse = new CardCommandResponse(savedCard);
        return new CommandResultResponse(201, cardCommandResponse);
    }
    
    public CardCollectionResponse findCollections() {
        List<Card> todoCards = cardRepository.findAllSameStatus(CardStatus.from("TODO"));
        List<Card> inProgressCards = cardRepository.findAllSameStatus(CardStatus.from("IN_PROGRESS"));
        List<Card> doneCards = cardRepository.findAllSameStatus(CardStatus.from("DONE"));

        return new CardCollectionResponse(todoCards, inProgressCards, doneCards);
    }

    public void modify(CardModifyRequest cardModifyRequest) {
        Card card = cardModifyRequest.toEntity();
        Card modifyedCard = cardRepository.update(card);
    }
}
