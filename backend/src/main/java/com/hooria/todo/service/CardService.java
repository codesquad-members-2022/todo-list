package com.hooria.todo.service;

import com.hooria.todo.aop.ExcludeLogging;
import com.hooria.todo.domain.Card;
import com.hooria.todo.dto.AddCardParam;
import com.hooria.todo.dto.CardResponse;
import com.hooria.todo.repository.CardRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

    @ExcludeLogging
    public List<CardResponse> selectAll() {
        return cardRepository.findAll().stream()
            .map(Card::toCardResponse)
            .collect(Collectors.toList());
    }

    public CardResponse add(AddCardParam addCardParam) {
        Card newCard = addCardParam.toEntity();
        long addedId = cardRepository.add(newCard);
        return cardRepository.findById(addedId)
            .map(Card::toCardResponse)
            .orElseThrow();
    }

    public CardResponse delete(long id) {
        long deletedId = cardRepository.delete(id);
        return cardRepository.findById(deletedId)
            .map(Card::toCardResponse)
            .orElseThrow();
    }
}
