package com.ijava.todolist.card.service;

import com.ijava.todolist.card.controller.dto.CardCreateRequest;
import com.ijava.todolist.card.controller.dto.CardMoveRequest;
import com.ijava.todolist.card.controller.dto.CardUpdateRequest;
import com.ijava.todolist.card.domain.Card;
import com.ijava.todolist.card.exception.CardNotFoundException;
import com.ijava.todolist.card.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {

    private final static int CARD_COUNT_DEFAULT = 0;

    private final CardRepository cardRepository;

    /**
     * 특정 칼럼에 속한 카드 목록 조회
     * @param columnsId
     * @return
     */
    public List<Card> findCardList(Long columnsId) {
        if (columnsId == null) return Collections.emptyList();

        return cardRepository.findByColumnId(columnsId)
                .orElseGet(Collections::emptyList);
    }

    /**
     * 특정 칼럼에 속한 카드 개수 조회
     * @param columnsId
     * @return
     */
    public int getCountOfCardsOnColumns(Long columnsId) {
        return cardRepository.getCountOfCardsOnColumns(columnsId)
                .orElse(CARD_COUNT_DEFAULT);

    }

    /**
     * id 로 카드 조회
     * @param id
     * @return
     */
    public Card findCardById(Long id) {
        return cardRepository.findById(id)
                .orElseThrow(CardNotFoundException::new);
    }

    /**
     * 카드 저장 요청 시, 카드 생성
     * @param request
     * @return
     */
    public Card saveNewCard(CardCreateRequest request) {
        LocalDateTime createdDate = LocalDateTime.now();
        Card newCard = Card.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .columnsId(request.getColumnId())
                .createdDate(createdDate)
                .modifiedDate(createdDate)
                .build();

        Card savedCard = cardRepository.save(newCard);

        return savedCard;
    }

    /**
     * 카드 수정 요청 시, 존재하는 카드이면 수정후, 수정된 카드를 반환함
     * @param cardId
     * @param updateRequest
     * @return
     */
    public Card updateCard(Long cardId, CardUpdateRequest updateRequest) {
        Card updateTargetCard = cardRepository.findById(cardId)
                .orElseThrow(CardNotFoundException::new);

        updateTargetCard.updateTitle(updateRequest.getTitle());
        updateTargetCard.updateContent(updateRequest.getContent());
        updateTargetCard.changeModifiedDate();

        return cardRepository.save(updateTargetCard);
    }

    public Card moveCard(CardMoveRequest cardMoveRequest) {
        Card moveTargetCard = cardRepository.findById(cardMoveRequest.getCardId())
                .orElseThrow(CardNotFoundException::new);

        moveTargetCard.moveColumn(cardMoveRequest.getColumnId());
        moveTargetCard.changeModifiedDate();

        return cardRepository.save(moveTargetCard);
    }

    public Long deleteCard(Long id) {
        Card deleteTargetCard = findCardById(id);
        deleteTargetCard.delete();
        deleteTargetCard.changeModifiedDate();

        cardRepository.save(deleteTargetCard);

        return deleteTargetCard.getId();
    }
}
