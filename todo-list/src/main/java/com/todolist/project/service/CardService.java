package com.todolist.project.service;

import com.todolist.project.domain.card.Card;
import com.todolist.project.domain.card.CardRepository;
import com.todolist.project.web.dto.CardAddDto;
import com.todolist.project.web.dto.CardListDto;
import com.todolist.project.web.dto.CardUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CardService {
    private final CardRepository cardRepository;

    public CardUpdateDto findUpdateCard(Long id){
        return CardUpdateDto.makeUpdateDto(cardRepository.findCardById(id));
    }

    public int addCard(CardAddDto cardAddDto, int size) {
        return cardRepository.add(cardAddDto.toEntity(), size);
    }

    public int removeCard(Long id) {
        return cardRepository.remove(id);
    }


    public List<CardListDto> findAll() {
        return cardRepository.findAll();
    }

    public List<CardListDto> findByStatus(String cardStatus) { return cardRepository.findCardsByStatus(cardStatus);}

    public int updateCard(Long id, CardUpdateDto cardUpdateDto) {
        return cardRepository.update(id, cardUpdateDto.toEntity());
    }

}
