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
        card.update(dto.getTitle(), dto.getContent());
        return cardRepository.save(card);
    }

    public Long delete(Long id) {
        Card card = findById(id);
        validateAlreadyDeleted(card);
        return cardRepository.delete(card);
    }

    private void validateAlreadyDeleted(Card card) {
        if (card.isDeleted()) {
            throw new IllegalStateException("이미 삭제된 카드입니다.");
        }
    }

    public Long move(CardMoveRequestDto dto) {
        return cardRepository.move(dto);
    }

    public Card findById(Long id) {
        Optional<Card> card = cardRepository.findById(id);
        validateCardIsNonNull(card);
        return card.get();
    }

    private void validateCardIsNonNull(Optional<Card> card) {
        if (card.isEmpty()) {
            throw new IllegalArgumentException("해당 카드가 존재하지 않습니다.");
        }
    }
}
