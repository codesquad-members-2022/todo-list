package com.codesquad.todolist.card;

import org.springframework.stereotype.Service;

import com.codesquad.todolist.Util.DtoConverter;

@Service
public class CardService {

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public Card create(CardCreateRequest createRequest) {
        Card card = DtoConverter.dtoToEntity(createRequest);
        return cardRepository.create(card);
    }
}
