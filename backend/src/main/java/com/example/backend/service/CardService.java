package com.example.backend.service;

import com.example.backend.domain.ActionType;
import com.example.backend.domain.Card;
import com.example.backend.domain.repository.CardRepository;
import com.example.backend.domain.repository.LogJdbcTemplateRepository;
import com.example.backend.web.dto.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CardService {

    private final CardRepository cardRepository;
    private final LogJdbcTemplateRepository logJdbcTemplateRepository;

    public CardService(CardRepository cardRepository, LogJdbcTemplateRepository logJdbcTemplateRepository) {
        this.cardRepository = cardRepository;
        this.logJdbcTemplateRepository = logJdbcTemplateRepository;
    }

    public Columns findAll() {
        return cardRepository.findAllDesc();
    }

    @Transactional
    public CardListResponseDto save(CardSaveRequestDto dto) {
        String title = dto.getTitle();
        String columnName = dto.getColumnName();
        LogSaveRequestDto logSaveRequestDto = new LogSaveRequestDto.Builder()
                .title(title)
                .curColumnName(columnName)
                .actionType(ActionType.ADD)
                .build();
        logJdbcTemplateRepository.save(logSaveRequestDto.toEntity());

        return cardRepository.save(dto.toEntity());
    }

    @Transactional
    public CardListResponseDto update(Long id, CardUpdateRequestDto dto) {
        Card card = findById(id);
        card.update(dto.getTitle(), dto.getContent());

        String title = dto.getTitle();
        LogSaveRequestDto logSaveRequestDto = new LogSaveRequestDto.Builder()
                .title(title)
                .actionType(ActionType.UPDATE)
                .build();
        logJdbcTemplateRepository.save(logSaveRequestDto.toEntity());

        return cardRepository.save(card);
    }

    @Transactional
    public Long delete(Long id) {
        Card card = findById(id);

        LogSaveRequestDto logSaveRequestDto = new LogSaveRequestDto.Builder()
                .title(card.getTitle())
                .curColumnName(card.getColumnName())
                .actionType(ActionType.REMOVE)
                .build();
        logJdbcTemplateRepository.save(logSaveRequestDto.toEntity());

        return cardRepository.delete(card);
    }

    public Columns move(CardMoveRequestDto dto) {
        Long id = dto.getId();
        Card originalCard = findById(id);
        String title = originalCard.getTitle();
        String columnName = originalCard.getColumnName();
        Card movedCard = dto.toEntity();

        if (!originalCard.isSameColumnWith(movedCard)) {
            LogSaveRequestDto logSaveRequestDto = new LogSaveRequestDto.Builder()
                    .title(title)
                    .prevColumnName(columnName)
                    .curColumnName(dto.getNewColumnName())
                    .actionType(ActionType.MOVE)
                    .build();
            logJdbcTemplateRepository.save(logSaveRequestDto.toEntity());
        }
        return cardRepository.move(originalCard, movedCard);
    }

    public Card findById(Long id) {
        return validateCardById(id);
    }

    private Card validateCardById(Long id) {
        return cardRepository.findById(id).stream()
                .filter(card -> !card.isDeleted())
                .findAny()
                .orElseThrow(
                        () -> new IllegalArgumentException("해당 카드가 삭제되었거나 존재하지 않습니다.")
                );
    }
}
