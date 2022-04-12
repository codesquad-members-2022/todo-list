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
    private static final Integer INCREMENT = 1000;

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public CardDTO save(CardDTO cardDto) {
        /*
        해당 섹션의 카드 수를 구해와서 order 값과 비교한다. 이 때 같아야만 통과된다.
         */
        int numberOfCards = cardRepository.findNumberOfCards(cardDto.getSection());
        if (cardDto.getOrder() != numberOfCards){
            throw new IllegalStateException("섹션의 카드 수와 일치하지 않습니다.");
        }

        Card card = new Card(cardDto.getOrder()*INCREMENT, NON_DELETED, cardDto.getTitle(), cardDto.getContent(),
            cardDto.getSection());

        int newCardId = cardRepository.save(card);
        CardDTO newCardDto = new CardDTO(card.getOrder(), card.getTitle(), card.getContent(), null,
            card.getSectionType());
        newCardDto.setCardId(newCardId);
        return newCardDto;
    }


    public Card findOne(int id) throws NoSuchElementException {
        Optional<Card> card = cardRepository.findById(id);
        return card.orElseThrow();
    }

    public CardDTO update(int id, CardDTO cardDto) {
        Card updateTargetCard = findOne(id);

        updateTargetCard.changeTitle(cardDto.getTitle());
        updateTargetCard.changeOrder(cardDto.getOrder());
        updateTargetCard.changeContent(cardDto.getContent());
        updateTargetCard.changeSection(cardDto.getSection());

        cardRepository.save(updateTargetCard);

        CardDTO updatedCardDTO = new CardDTO(updateTargetCard.getOrder(), updateTargetCard.getTitle(),
            updateTargetCard.getContent(), null, updateTargetCard.getSectionType());
        updatedCardDTO.setCardId(id);
        return updatedCardDTO;

    }

    public CardDTO delete(int id) {
        int deletedId = cardRepository.delete(id);
        Card card = cardRepository.findById(deletedId)
            .orElseThrow(() -> new IllegalStateException("삭제를 위한 카드가 존재하지 않습니다."));
        CardDTO cardDto = new CardDTO(card.getOrder(), card.getTitle(), card.getContent(), null, card.getSectionType());
        cardDto.setCardId(deletedId);
        return cardDto;
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
                cardDto = new CardDTO(card.getOrder(), card.getTitle(), card.getContent(), null,
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
