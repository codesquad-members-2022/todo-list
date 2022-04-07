package todolist.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import todolist.domain.Card;
import todolist.dto.CardDto;
import todolist.repository.TodoRepository;

@Service
@RequiredArgsConstructor
public class CardService {

    private final TodoRepository<Card> repository;

    public CardDto addCard(CardDto cardDto) {
        Card card = repository.save(cardDto.toCard());
        return card.toCardDto();
    }
}
