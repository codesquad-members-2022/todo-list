package kr.codesquad.todolist.service;

import kr.codesquad.todolist.domain.Card;
import kr.codesquad.todolist.domain.Section;
import kr.codesquad.todolist.dto.card.CardResponse;
import kr.codesquad.todolist.dto.card.CreateCardRequest;
import kr.codesquad.todolist.dto.card.UpdateCardRequest;
import kr.codesquad.todolist.dto.section.CardsOfSection;
import kr.codesquad.todolist.repository.card.CardRepository;
import org.springframework.stereotype.Service;

import java.util.*;
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

    public CardResponse update(Long id, UpdateCardRequest cardRequest) {

        CardResponse target = findOne(id);
        Card updatableCard = Card.updateCard(id, target.getAuthor(), target.getSectionId(),
                cardRequest.getSubject(), cardRequest.getContents());

        Card updated = cardRepository.save(updatableCard);

        return CardResponse.from(updated);
    }

    public boolean move(Integer sectionId, Long targetCardId, Long movingCardId) {
        return cardRepository.move(sectionId, targetCardId, movingCardId);
    }

    public List<CardResponse> findCardsOfSection(Integer sectionId) {
        return cardRepository.findBySectionId(sectionId)
                .stream()
                .map(CardResponse::from)
                .collect(Collectors.toList());
    }

    public CardResponse findOne(Long id) {
        Card card = cardRepository.findById(id).orElseThrow(() -> new NoSuchElementException("카드를 찾을 수 없습니다."));
        return CardResponse.from(card);
    }

    public List<CardsOfSection> findAllbySections() {
        List<Section> sections = cardRepository.findSections();

        List<CardsOfSection> cardsOfSections = new ArrayList<>();
        for (Section section : sections) {
            List<CardResponse> cards = findCardsOfSection(section.getId());
            cardsOfSections.add(new CardsOfSection(section, cards));
        }

        return cardsOfSections;
    }

    public boolean delete(Long id) {
        return cardRepository.delete(id);
    }

    public Section findSection(Integer sectionId) {
        return cardRepository.findSection(sectionId).orElseThrow(() -> new NoSuchElementException("섹션을 찾을 수 없습니다."));
    }

}
