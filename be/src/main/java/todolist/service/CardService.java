package todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import todolist.domain.card.Card;
import todolist.dto.card.RequestCardDto;
import todolist.dto.card.ResponseCardDto;
import todolist.dto.card.ResponseCardsDto;
import todolist.repository.CardRepository;

import java.util.*;

@Service
public class CardService {

    private final CardRepository<Card> repository;

    @Autowired
    public CardService(CardRepository<Card> repository) {
        this.repository = repository;
    }

    public ResponseCardsDto getCards() {
        List<Card> cards = repository.findAll();

        ResponseCardsDto responseCardsDto = new ResponseCardsDto();
        responseCardsDto.categorizeCards(cards);
        return responseCardsDto;
    }

    public ResponseCardDto addCard(RequestCardDto requestCardDto) {
        Card card = repository.save(requestCardDto.toCard());
        return card.toResponseCardDto();
    }

    public ResponseCardDto updateCard(Long id, RequestCardDto requestCardDto) {
        Card card = repository.findById(id);
        card.update(requestCardDto);
        repository.update(card);
        return card.toResponseCardDto();
    }

    public ResponseCardDto deleteCard(Long id) {
        Card card = repository.findById(id);
        repository.delete(id);
        return card.toResponseCardDto();
    }

    public String getPrevSection(Long id) {
        Card card = repository.findById(id);
        return card.getSection();
    }
}
