package com.example.backend.domain.repository;

import com.example.backend.domain.Card;
import com.example.backend.web.dto.Column;
import com.example.backend.web.dto.CardMoveRequestDto;

import java.util.Optional;

public interface CardRepository {

    Column findAllDesc();

    Long save(Card card);

    Optional<Card> findById(Long id);

    Long deleteById(Long id);

    Long move(CardMoveRequestDto dto);
}
