package kr.codesquad.todolist.service;

import kr.codesquad.todolist.domain.Card;
import kr.codesquad.todolist.dto.CardDto;
import kr.codesquad.todolist.dto.CardResponse;
import kr.codesquad.todolist.repository.CardRepository;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public CardResponse create(CardDto cardDto){
        Card saved = cardRepository.save(cardDto.toEntity());
        return CardResponse.from(saved);
    }
}
