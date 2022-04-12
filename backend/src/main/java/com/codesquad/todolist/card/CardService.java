package com.codesquad.todolist.card;

import com.codesquad.todolist.card.dto.CardCreateRequest;
import com.codesquad.todolist.card.dto.CardMoveRequest;
import com.codesquad.todolist.card.dto.CardUpdateRequest;
import com.codesquad.todolist.history.HistoryRepository;
import com.codesquad.todolist.history.ModifiedFieldRepository;
import com.codesquad.todolist.history.domain.Action;
import com.codesquad.todolist.history.domain.History;
import com.codesquad.todolist.history.domain.ModifiedField;
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

    public Card create(CardCreateRequest request) {
        Card card = request.toEntity();
        return cardRepository.create(card);
    }

    public History update(Integer cardId, CardUpdateRequest request) {
        Card card = cardRepository.findById(cardId)
            .orElseThrow(() -> new IllegalArgumentException("카드를 찾을 수 없습니다."));

        card.update(request.getTitle(), request.getContent(), request.getAuthor());
        cardRepository.update(card);

        History history = new History(card.getCardId(), Action.UPDATE);
        historyRepository.create(history);

        List<ModifiedField> modifiedFields = card.getModifiedFields();
        for (ModifiedField modifiedField : modifiedFields) {
            modifiedField.setHistoryId(history.getHistoryId());
        }
        modifiedFieldRepository.createAll(modifiedFields);

        return historyRepository.findById(history.getHistoryId())
            .orElseThrow(() -> new IllegalArgumentException("히스토리를 찾을 수 없습니다."));
    }

    public void delete(Integer cardId) {
        Card card = cardRepository.findById(cardId)
            .orElseThrow(() -> new IllegalArgumentException("카드를 찾을 수 없습니다."));

        cardRepository.linkPrev(card);
        cardRepository.deleteTarget(card);
    }

    public void move(Integer cardId, CardMoveRequest request) {
        Card card = cardRepository.findById(cardId)
            .orElseThrow(() -> new IllegalArgumentException("카드를 찾을 수 없습니다."));

        // 이동할 카드의 기존 (old) 위치에서, 다음 (next) 노드의 카드 ID
        Integer oldNextId = card.getNextId();

        // 이동할 카드의 기존 (prev) 위치에서, 이전 노드를 이동할 카드의 다음 노드와 연결
        cardRepository.linkPrev(card);

        // 이동할 카드의 다음 (next) 위치에서, 이전 노드를 이동한 카드에 연결
        card.move(request.getColumnId(), request.getNextId());
        cardRepository.linkNext(card);
        cardRepository.moveTarget(card);
    }
}
