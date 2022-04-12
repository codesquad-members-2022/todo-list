package com.codesquad.todolist.card;

import org.springframework.stereotype.Service;

import com.codesquad.todolist.card.dto.CardCreateRequest;
import com.codesquad.todolist.card.dto.CardMoveRequest;
import com.codesquad.todolist.card.dto.CardUpdateRequest;

@Service
public class CardService {

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public Card create(CardCreateRequest request) {
        Card card = request.toEntity();
        return cardRepository.create(card);
    }

    public void update(Integer cardId, CardUpdateRequest request) {
        Card card = cardRepository.findById(cardId)
            .orElseThrow(() -> new IllegalArgumentException("카드를 찾을 수 없습니다."));

        card.update(request.getTitle(), request.getContent(), request.getAuthor());
        cardRepository.update(card);
    }

    public void delete(Integer cardId) {
        Card card = cardRepository.findById(cardId).orElseThrow(() -> new IllegalArgumentException("카드를 찾을 수 없습니다."));
        cardRepository.delete(card);
    }

    public void move(Integer cardId, CardMoveRequest request) {
        Card card = cardRepository.findById(cardId).orElseThrow(() -> new IllegalArgumentException("카드를 찾을 수 없습니다."));
        Integer oldNextId = card.getNextId(); // 이동 되기 전의 nextId
        card.move(request.getColumnId(), request.getNextId());
        cardRepository.move(oldNextId, card);

    }
}
