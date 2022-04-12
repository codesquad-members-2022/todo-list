package com.codesquad.todolist.card;

import com.codesquad.todolist.card.dto.CardCreateRequest;
import com.codesquad.todolist.card.dto.CardMoveRequest;
import com.codesquad.todolist.card.dto.CardUpdateRequest;
import com.codesquad.todolist.history.HistoryRepository;
import com.codesquad.todolist.history.ModifiedFieldRepository;
import com.codesquad.todolist.history.domain.Action;
import com.codesquad.todolist.history.domain.Field;
import com.codesquad.todolist.history.domain.History;
import com.codesquad.todolist.history.domain.ModifiedField;
import com.codesquad.todolist.history.dto.HistoryResponse;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    private final CardRepository cardRepository;
    private final HistoryRepository historyRepository;
    private final ModifiedFieldRepository modifiedFieldRepository;

    public CardService(CardRepository cardRepository, HistoryRepository historyRepository,
        ModifiedFieldRepository modifiedFieldRepository) {
        this.cardRepository = cardRepository;
        this.historyRepository = historyRepository;
        this.modifiedFieldRepository = modifiedFieldRepository;
    }

    public HistoryResponse create(CardCreateRequest request) {
        Card card = request.toEntity();
        cardRepository.create(card);

        // History 저장
        History history = new History(card.getCardId(), Action.CREATE);
        historyRepository.create(history);

        return getHistoryResponse(history.getHistoryId());
    }

    public HistoryResponse update(Integer cardId, CardUpdateRequest request) {
        Card card = cardRepository.findById(cardId)
            .orElseThrow(() -> new IllegalArgumentException("카드를 찾을 수 없습니다."));

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

        return getHistoryResponse(history.getHistoryId());
    }

    public HistoryResponse delete(Integer cardId) {
        Card card = cardRepository.findById(cardId)
            .orElseThrow(() -> new IllegalArgumentException("카드를 찾을 수 없습니다."));

        // Column 내 Card 간 링크 및 삭제
        cardRepository.linkPrev(card);
        cardRepository.deleteTarget(card);

        // History 저장
        History history = new History(card.getCardId(), Action.DELETE);
        historyRepository.create(history);

        return getHistoryResponse(history.getHistoryId());
    }

    public HistoryResponse move(Integer cardId, CardMoveRequest request) {
        Card oldCard = cardRepository.findById(cardId)
            .orElseThrow(() -> new IllegalArgumentException("카드를 찾을 수 없습니다."));

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

        return getHistoryResponse(history.getHistoryId());
    }

    private HistoryResponse getHistoryResponse(Integer historyId) {
        History history = historyRepository.findById(historyId)
            .orElseThrow(() -> new IllegalArgumentException("히스토리를 찾을 수 없습니다."));

        if (history.getAction() == Action.MOVE || history.getAction() == Action.UPDATE) {
            List<ModifiedField> modifiedFields = modifiedFieldRepository.findByHistoryId(historyId);
            history.setFields(modifiedFields);
        }
        return HistoryResponse.from(history);
    }
}
