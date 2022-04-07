package todo.list.service;

import org.springframework.stereotype.Service;
import todo.list.domain.Card;
import todo.list.repository.CardRepository;

@Service
public class CardService {

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public void save(CardSaveDto cardSaveDto) {
//        Card card = cardSaveDto.toEntity();
//        cardRepository.save();
    }
}
