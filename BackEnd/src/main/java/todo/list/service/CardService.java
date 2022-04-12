package todo.list.service;

import org.springframework.stereotype.Service;
import todo.list.service.dto.CardModifyDto;
import todo.list.domain.Card;
import todo.list.domain.CardStatus;
import todo.list.repository.CardRepository;
import todo.list.service.dto.CardDto;
import todo.list.service.dto.CardResponse;
import todo.list.service.dto.CardSaveDto;

import java.util.List;
import java.util.stream.Collectors;

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
    
    public CardResponse findCollections() {
        List<Card> cards = cardRepository.findAll();

        List<CardDto> todoCollection = makeStatusCollection(cards, CardStatus.from("TODO"));
        List<CardDto> inProgressCollection = makeStatusCollection(cards, CardStatus.from("IN_PROGRESS"));
        List<CardDto> doneCollection = makeStatusCollection(cards, CardStatus.from("DONE"));

        return new CardResponse(todoCollection, inProgressCollection, doneCollection);
    }

    private List<CardDto> makeStatusCollection(List<Card> cards, CardStatus cardStatus) {
        return cards.stream()
                .filter(card -> card.equalsStatus(cardStatus))
                .map(card -> new CardDto(card))
                .collect(Collectors.toList());
    }

    public void modify(CardModifyDto cardModifyDto) {
        Card card = cardModifyDto.toEntity();
        cardRepository.update(card);
    }
}
