package com.example.backend.domain.repository;

import com.example.backend.domain.Card;
import com.example.backend.domain.Column;
import com.example.backend.web.dto.CardsMoveRequestDto;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository {

    Column findAllDesc();

    Long save(Card card);

    Optional<Card> findById(Long id);

    Long deleteById(Long id);

    Long move(CardsMoveRequestDto dto);
}
