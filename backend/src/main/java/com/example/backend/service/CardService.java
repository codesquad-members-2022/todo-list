package com.example.backend.service;

import com.example.backend.domain.Card;
import com.example.backend.domain.repository.CardRepository;
import com.example.backend.web.dto.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardService {

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public Columns findAll() {
        return cardRepository.findAllDesc();
    }

    public CardListResponseDto save(CardSaveRequestDto dto) {
        return cardRepository.save(dto.toEntity());
    }

    public CardListResponseDto update(Long id, CardUpdateRequestDto dto) {
        Card card = findById(id);
        card.update(dto.getTitle(), dto.getTitle());
        return cardRepository.save(card);
    }

    public Long delete(Long id) {
        return cardRepository.deleteById(id);
    }

    public Long move(CardMoveRequestDto dto) {
        return cardRepository.move(dto);
    }

    public Card findById(Long id) {
        Optional<Card> card = cardRepository.findById(id);
        if (card.isEmpty()) {
            throw new IllegalArgumentException("해당 카드가 존재하지 않습니다.");
        }
        return card.get();
    }
}
