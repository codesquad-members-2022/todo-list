package com.team05.todolist.service;

import com.team05.todolist.domain.Card;
import com.team05.todolist.domain.dto.CardDTO;
import com.team05.todolist.domain.dto.ClassifiedCardsDTO;
import com.team05.todolist.repository.CardRepository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    private static final Integer NON_DELETED = 0;

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public CardDTO save(CardDTO cardDto) {
        Card card = new Card(cardDto.getOrder(), NON_DELETED, cardDto.getTitle(), cardDto.getContent(),
            cardDto.getSection());

        int newCardId = cardRepository.save(card);
        CardDTO newCardDto = new CardDTO(card.getOrder(), card.getTitle(), card.getContent(),
            card.getSectionType());
        newCardDto.setCardId(newCardId);
        return newCardDto;
    }


    public Card findOne(int id) throws NoSuchElementException {
        Optional<Card> card = cardRepository.findById(id);
        return card.orElseThrow();
    }

    public void update(int id, CardDTO cardDto) {
        Card updateTargetCard = findOne(id);

        updateTargetCard.changeTitle(cardDto.getTitle());
        updateTargetCard.changeOrder(cardDto.getOrder());
        updateTargetCard.changeContent(cardDto.getContent());
        updateTargetCard.changeSection(cardDto.getSection());

        cardRepository.save(updateTargetCard);
    }

    public void delete(int id) {
        cardRepository.delete(id);
    }

    public ClassifiedCardsDTO findCards() {
        List<Card> cards = cardRepository.findAll();
		return classifyBySection(cards);
    }

	private ClassifiedCardsDTO classifyBySection(List<Card> cards) {
        ClassifiedCardsDTO classifiedCards = new ClassifiedCardsDTO();
        List<CardDTO> sectionCards;
        CardDTO cardDto;
        for (Card card : cards) {
            sectionCards = classifiedCards.get(card.getSectionType());
            if (check10Cards(sectionCards)) {
                cardDto = new CardDTO(card.getOrder(), card.getTitle(), card.getContent(),
                    card.getSectionType());
                cardDto.setCardId(card.getId());
                sectionCards.add(cardDto);
            }
        }
        return classifiedCards;
    }

    private boolean check10Cards(List<CardDTO> cards) {
        return cards.size() < 10;
    }
}
