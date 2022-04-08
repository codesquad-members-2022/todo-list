package todolist.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import todolist.domain.Card;
import todolist.dto.RequestCardDto;
import todolist.dto.ResponseCardDto;
import todolist.repository.TodoRepository;

@Service
@RequiredArgsConstructor
public class CardService {

    private final TodoRepository<Card> repository;

    public ResponseCardDto addCard(RequestCardDto requestCardDto) {
        Card card = repository.save(requestCardDto.toCard());
        return card.toResponseCardDto();
    }
}
