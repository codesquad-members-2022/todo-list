package todolist.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import todolist.dto.CardsDto;
import todolist.repository.TodoRepository;

@Service
@RequiredArgsConstructor
public class CardService {

    private final TodoRepository repository;

    public CardsDto getCardList() {
        return null;
    }
}
