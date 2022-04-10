package com.team05.todolist.service;

import com.team05.todolist.domain.Card;
import com.team05.todolist.domain.dto.CardDTO;
import com.team05.todolist.repository.CardRepository;
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
}
