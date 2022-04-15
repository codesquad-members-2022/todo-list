package todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import todolist.domain.card.Card;
import todolist.domain.card.Section;
import todolist.domain.event.Action;
import todolist.dto.card.RequestCardDto;
import todolist.dto.card.ResponseCardDto;
import todolist.dto.card.ResponseCardsDto;
import todolist.dto.event.RequestEventDto;
import todolist.repository.CardRepository;

import java.util.List;

@Service
public class CardService {

    private final EventService eventService;
    private final CardRepository<Card> repository;
    
    public CardService(EventService eventService, CardRepository<Card> repository) {
        this.eventService = eventService;
        this.repository = repository;
    }

    public ResponseCardsDto getCards() {
        List<Card> cards = repository.findAll();
        return new ResponseCardsDto(Section.categorizeCards(cards));
    }

    @Transactional
    public ResponseCardDto addCard(RequestCardDto requestCardDto) {
        Card card = repository.save(requestCardDto.toCard());
        eventService.addEvent(new RequestEventDto(card), Action.ADD);
        return card.toResponseCardDto();
    }

    @Transactional
    public ResponseCardDto updateCard(Long id, RequestCardDto requestCardDto) {
        Card card = repository.findById(id);
        String prevSection = card.getSection();

        card.update(requestCardDto);
        repository.update(card);

        if (card.isSectionUpdated(prevSection)) {
            eventService.addEvent(new RequestEventDto(card), Action.UPDATE);
            return card.toResponseCardDto();
        }

        eventService.addEvent(new RequestEventDto(prevSection, card), Action.MOVE);
        return card.toResponseCardDto();
    }

    @Transactional
    public ResponseCardDto deleteCard(Long id) {
        Card card = repository.findById(id);
        repository.delete(id);

        eventService.addEvent(new RequestEventDto(card), Action.REMOVE);

        return card.toResponseCardDto();
    }
}
