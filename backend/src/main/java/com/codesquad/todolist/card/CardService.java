package com.codesquad.todolist.card;

import com.codesquad.todolist.card.dto.BundleResponse;
import com.codesquad.todolist.card.dto.CardCreateRequest;
import com.codesquad.todolist.card.dto.CardMoveRequest;
import com.codesquad.todolist.card.dto.CardResponse;
import com.codesquad.todolist.card.dto.CardUpdateRequest;
import com.codesquad.todolist.column.ColumnRepository;
import com.codesquad.todolist.exception.BusinessException;
import com.codesquad.todolist.exception.ErrorCode;
import com.codesquad.todolist.exception.NotFoundException;
import com.codesquad.todolist.history.HistoryRepository;
import com.codesquad.todolist.history.ModifiedFieldRepository;
import com.codesquad.todolist.history.domain.Action;
import com.codesquad.todolist.history.domain.Field;
import com.codesquad.todolist.history.domain.History;
import com.codesquad.todolist.history.domain.ModifiedField;
import com.codesquad.todolist.history.dto.HistoryResponse;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    private final CardRepository cardRepository;
    private final ColumnRepository columnRepository;
    private final HistoryRepository historyRepository;
    private final ModifiedFieldRepository modifiedFieldRepository;

    public CardService(CardRepository cardRepository, ColumnRepository columnRepository,
        HistoryRepository historyRepository, ModifiedFieldRepository modifiedFieldRepository) {
        this.cardRepository = cardRepository;
        this.columnRepository = columnRepository;
        this.historyRepository = historyRepository;
        this.modifiedFieldRepository = modifiedFieldRepository;
    }

    public BundleResponse create(CardCreateRequest request) {
        Integer nextId = findLastCardId(request.getColumnId());
        Card card = request.toEntity(nextId);
        cardRepository.create(card);

        // History 저장
        History history = new History(card.getCardId(), Action.CREATE);
        historyRepository.create(history);

        return toResponse(history.getHistoryId(), card);
    }

    public BundleResponse update(Integer cardId, CardUpdateRequest request) {
        Card card = cardRepository.findById(cardId)
            .orElseThrow(() -> new NotFoundException(ErrorCode.CARD_NOT_FOUND));

        // Card 필드 업데이트 및 저장
        card.update(request.getTitle(), request.getContent(), request.getAuthor());
        cardRepository.update(card);

        // History 저장 및 ModifiedField 저장
        History history = new History(card.getCardId(), Action.UPDATE);
        historyRepository.create(history);

        List<ModifiedField> modifiedFields = card.getModifiedFields();
        for (ModifiedField modifiedField : modifiedFields) {
            modifiedField.setHistoryId(history.getHistoryId());
        }
        modifiedFieldRepository.createAll(modifiedFields);

        return toResponse(history.getHistoryId(), card);
    }

    public BundleResponse delete(Integer cardId) {
        Card card = cardRepository.findById(cardId)
            .orElseThrow(() -> new NotFoundException(ErrorCode.CARD_NOT_FOUND));

        // Column 내 Card 간 링크 및 삭제
        cardRepository.linkPrev(card);
        cardRepository.deleteTarget(card);

        // History 저장
        History history = new History(card.getCardId(), Action.DELETE);
        historyRepository.create(history);

        return toResponse(history.getHistoryId(), card);
    }

    public BundleResponse move(Integer cardId, CardMoveRequest request) {
        // 이동할 컬럼에서 지정된 다음 노드가 있는지 확인
        validateNextId(request.getColumnId(), request.getNextId());

        Card oldCard = cardRepository.findById(cardId)
            .orElseThrow(() -> new NotFoundException(ErrorCode.CARD_NOT_FOUND));

        Card newCard = oldCard.clone();
        newCard.move(request.getColumnId(), request.getNextId());

        // Column 내 Card 간 링크 및 이동
        cardRepository.linkPrev(oldCard);
        cardRepository.linkNext(newCard);
        cardRepository.moveTarget(newCard);

        // History 저장 및 ModifiedField 저장
        History history = new History(newCard.getCardId(), Action.MOVE);
        historyRepository.create(history);

        ModifiedField modifiedField = new ModifiedField(history.getHistoryId(), Field.COLUMN,
            oldCard.getColumnId().toString(),
            newCard.getColumnId().toString());
        modifiedFieldRepository.create(modifiedField);

        return toResponse(history.getHistoryId(), newCard);
    }

    private BundleResponse toResponse(Integer historyId, Card card) {
        History history = historyRepository.findById(historyId)
            .orElseThrow(() -> new NotFoundException(ErrorCode.HISTORY_NOT_FOUND));

        if (history.getAction() == Action.MOVE || history.getAction() == Action.UPDATE) {
            List<ModifiedField> modifiedFields = modifiedFieldRepository.findByHistoryId(historyId);
            history.setFields(modifiedFields);
        }
        return BundleResponse.of(
            CardResponse.from(card), HistoryResponse.from(history)
        );
    }

    private void validateNextId(int columnId, Integer nextId) {
        if (nextId == null) {
            return;
        }
        Integer count = cardRepository.countByColumnIdAndNextId(columnId, nextId);

        if (count == 0) {
            throw new NotFoundException(ErrorCode.NEXT_CARD_NOT_FOUND);
        }
    }

    public Integer findLastCardId(Integer columnId) {
        // column 이 존재하는지 확인
        columnRepository.findById(columnId)
            .orElseThrow(() -> new NotFoundException(ErrorCode.COLUMN_NOT_FOUND));

        // column 내 모든 card 객체를 조회
        List<Card> cards = cardRepository.findByColumnId(columnId);

        if (cards.size() == 0) {
            return null;
        }

        // map 자료구조에 nextId 별로 card 객체를 저장
        Map<Integer, Card> columnCardMap = cards.stream()
            .collect(Collectors.toMap(Card::getNextId, Function.identity()));

        // next Id 가 null 인 card (= 컬럼의 맨 마지막 카드) 를 조회
        Card card = Optional.ofNullable(columnCardMap.remove(null))
            .orElseThrow(() -> new BusinessException(ErrorCode.ILLEGAL_CARD_ORDER));

        // map 자료 구조에서 이전 카드의 card Id 가 next Id 인 card 를 추출
        while (columnCardMap.size() > 0) {
            card = columnCardMap.remove(card.getCardId());
        }

        // 컬럼 내 가장 마지막 card Id 를 반환
        return card.getCardId();
    }
}
