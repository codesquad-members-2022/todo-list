package kr.codesquad.todolist.service;

import kr.codesquad.todolist.repository.CardRepository;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }
}
