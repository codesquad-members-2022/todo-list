package com.example.backend.domain.repository;

import com.example.backend.domain.Card;
import com.example.backend.web.dto.CardListResponseDto;
import com.example.backend.web.dto.CardMoveRequestDto;
import com.example.backend.web.dto.Columns;

import java.util.Optional;

public interface CardRepository {

    Columns findAllDesc();

    CardListResponseDto save(Card card);

    Optional<Card> findById(Long id);

    Long deleteById(Long id);

    Long move(CardMoveRequestDto dto);
}
