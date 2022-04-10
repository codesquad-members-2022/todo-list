package com.todolist.project.service;

import com.todolist.project.domain.card.Card;
import com.todolist.project.domain.card.CardRepository;
import com.todolist.project.web.dto.CardAddDto;
import com.todolist.project.web.dto.CardUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CardService {
    private final CardRepository cardRepository;

    //TODO: 에러 핸들러를 만들어서 에러처리 필요
    public CardUpdateDto findUpdateCard(Long id){
        Card card = cardRepository.findCardById(id).orElseThrow(
                IllegalArgumentException::new
        );
        return makeUpdateDto(card);
    }

    public CardUpdateDto makeUpdateDto(Card card) {
        return new CardUpdateDto(card.getTitle(), card.getContents(), card.getCardStatus());
    }

    public int addCard(CardAddDto cardAddDto) {
        return cardRepository.add(cardAddDto);
    }

    public int removeCard(Long id) {
        return cardRepository.remove(id);
    }


    public List<Card> findAll() { return cardRepository.findAll(); }

    public int updateCard(Long id, CardUpdateDto cardUpdateDto) {
        return cardRepository.update(id, cardUpdateDto);
    }

}
