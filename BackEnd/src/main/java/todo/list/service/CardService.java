package todo.list.service;

import org.springframework.stereotype.Service;
import todo.list.service.dto.CardModifyDto;
import todo.list.domain.Card;
import todo.list.domain.CardStatus;
import todo.list.repository.CardRepository;
import todo.list.service.dto.CardCollectionResponse;
import todo.list.service.dto.CardSaveDto;

import java.util.List;

@Service
public class CardService {

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public void save(CardSaveDto cardSaveDto) {
        Card card = cardSaveDto.toEntity();
        cardRepository.save(card);
    }
    
    public CardCollectionResponse findCollections() {
        List<Card> todoCards = cardRepository.findAllSameStatus(CardStatus.from("TODO"));
        List<Card> inProgressCards = cardRepository.findAllSameStatus(CardStatus.from("IN_PROGRESS"));
        List<Card> doneCards = cardRepository.findAllSameStatus(CardStatus.from("DONE"));

        return new CardCollectionResponse(todoCards, inProgressCards, doneCards);
    }

    public void modify(CardModifyDto cardModifyDto) {
        Card card = cardModifyDto.toEntity();
        cardRepository.update(card);
    }
}
