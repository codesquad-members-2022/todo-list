package kr.codesquad.todolist.service;

import kr.codesquad.todolist.domain.Card;
import kr.codesquad.todolist.dto.card.CardResponse;
import kr.codesquad.todolist.dto.card.CreateCardRequest;
import kr.codesquad.todolist.dto.card.UpdateCardRequest;
import kr.codesquad.todolist.repository.CardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class CardService {

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public CardResponse create(CreateCardRequest cardRequest) {
        Card saved = cardRepository.save(cardRequest.toEntity());
        return CardResponse.from(saved);
    }

    public CardResponse update(UpdateCardRequest cardRequest) {
        Card updated = cardRepository.save(cardRequest.toEntity());
        return CardResponse.from(updated);
    }

    public boolean move(Integer sectionId, Long targetCardId, UpdateCardRequest cardRequest) {
        return cardRepository.move(sectionId, targetCardId, cardRequest.toEntity());
    }

    public List<CardResponse> findCardsOfSection(Integer sectionId) {
        return cardRepository.findBySectionId(sectionId)
                .stream()
                .map(CardResponse::from)
                .collect(Collectors.toList());
    }

    public CardResponse findOne(Long id) {
        Card card = cardRepository.findById(id).orElseThrow(NoSuchElementException::new);
        return CardResponse.from(card);
    }

    public List<CardResponse> findAll() {
        List<Card> cards = cardRepository.findAll();
        return cards.stream()
                .map(CardResponse::from)
                .collect(Collectors.toList());
    }

    public boolean delete(Long id) {
        return cardRepository.delete(id);
    }

}
