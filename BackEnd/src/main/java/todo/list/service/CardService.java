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
        List<Card> todoCards = cardRepository.findAllSameStatus(CardStatus.from("TODO"));
        List<Card> inProgressCards = cardRepository.findAllSameStatus(CardStatus.from("IN_PROGRESS"));
        List<Card> doneCards = cardRepository.findAllSameStatus(CardStatus.from("DONE"));

        return new CardResponse(makeCollection(todoCards), makeCollection(inProgressCards), makeCollection(doneCards));
    }

    private List<CardDto> makeCollection(List<Card> cards) {
        return cards.stream()
                .map(card -> new CardDto(card))
                .collect(Collectors.toList());
    }

    public void modify(CardModifyDto cardModifyDto) {
        Card card = cardModifyDto.toEntity();
        cardRepository.update(card);
    }
}
