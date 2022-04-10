package todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import todolist.domain.card.Card;
import todolist.dto.card.RequestCardDto;
import todolist.dto.card.ResponseCardDto;
import todolist.repository.TodoRepository;

import java.util.*;

@Service
public class CardService {

    private final TodoRepository<Card> repository;

    @Autowired
    public CardService(TodoRepository<Card> repository) {
        this.repository = repository;
    }

    public Map<String, List<Card>> getCards() {
        List<Card> cards = repository.findAll();
        return categorizeCards(cards);
    }

    public ResponseCardDto addCard(RequestCardDto requestCardDto) {
        Card card = repository.save(requestCardDto.toCard());
        return card.toResponseCardDto();
    }

    public void updateCard(Long id, RequestCardDto requestCardDto) {
        Card existingCard = repository.findById(id);
        existingCard.update(requestCardDto);
    }

    public void deleteCard(Long id) {
        repository.delete(id);
    }

    private Map<String, List<Card>> categorizeCards(List<Card> cards) {
        Map<String, List<Card>> result = new HashMap<>();

        for (Card card : cards) {
            String section = card.getSection();
            if (result.containsKey(section)) {
                List<Card> cardList = result.get(section);
                cardList.add(card);
            } else {
                result.put(section, new ArrayList<>(Arrays.asList(card)));
            }
        }
        return result;
    }
}
