package com.example.backend.service;

import com.example.backend.domain.Card;
import com.example.backend.domain.repository.CardRepository;
import com.example.backend.domain.Column;
import com.example.backend.web.dto.CardSaveRequestDto;
import com.example.backend.web.dto.CardMoveRequestDto;
import com.example.backend.web.dto.CardUpdateRequestDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardService {

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public Column findAll() {
        return cardRepository.findAllDesc();
    }

    public Long save(CardSaveRequestDto dto) {
        return cardRepository.save(dto.toEntity());
    }

    public Long update(Long id, CardUpdateRequestDto dto) {
        // 해당 id로 조회한 카드의 entity에 내용을 반영하여 전달
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
