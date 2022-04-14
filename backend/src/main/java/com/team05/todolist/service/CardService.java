package com.team05.todolist.service;

import com.team05.todolist.domain.Card;
import com.team05.todolist.domain.Section;
import com.team05.todolist.domain.dto.CardDTO;
import com.team05.todolist.domain.dto.ClassifiedCardsDTO;
import com.team05.todolist.domain.dto.CreateCardDTO;
import com.team05.todolist.domain.dto.MoveCardDTO;
import com.team05.todolist.domain.dto.UpdateCardDTO;
import com.team05.todolist.repository.CardRepository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    private static final Integer NON_DELETED = 0;
    private static final Integer INCREMENT = 1000;
    private final Logger logger = LoggerFactory.getLogger(CardService.class);

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public CardDTO save(CreateCardDTO cardDto, String userAgent) {
        /*
        해당 섹션의 카드 수를 구해와서 order 값과 비교한다. 이 때 같아야만 통과된다.
         */
        if (cardDto.getNumber() != 0) {
            int numberOfCards = cardRepository.findNumberOfCards(cardDto.getSection());
            logger.debug("{}-card 개수: {}", cardDto.getSection(), cardDto.getNumber());
            if (cardDto.getNumber() != numberOfCards){
                logger.debug("실제 DB의 {}-card 개수: {}", cardDto.getSection(), numberOfCards);
                throw new IllegalStateException("섹션의 카드 수와 일치하지 않습니다.");
            }
        }

        Card card = new Card((cardDto.getNumber()+1)*INCREMENT, NON_DELETED, cardDto.getTitle(), cardDto.getContent(),
            cardDto.getSection(), userAgent);

        int newCardId = cardRepository.save(card);
        CardDTO newCardDto = new CardDTO(card.getOrder(), card.getTitle(), card.getContent(), card.getSectionType(), userAgent);
        newCardDto.setCardId(newCardId);
        return newCardDto;
    }


    public Card findOne(int id) throws NoSuchElementException {
        Optional<Card> card = cardRepository.findById(id);
        return card.orElseThrow();
    }

    public CardDTO update(int id, UpdateCardDTO updateCardDto, String userAgent) {
        Card updateTargetCard = findOne(id);

        updateTargetCard.changeTitle(updateCardDto.getTitle());
        updateTargetCard.changeOrder(updateCardDto.getOrder());
        updateTargetCard.changeContent(updateCardDto.getContent());
        updateTargetCard.changeSection(updateCardDto.getSection());
        updateTargetCard.changeAuthor(userAgent);

        cardRepository.save(updateTargetCard);

        CardDTO updatedCardDTO = new CardDTO(updateTargetCard.getOrder(), updateTargetCard.getTitle(),
            updateTargetCard.getContent(), updateTargetCard.getSectionType(), userAgent);
        updatedCardDTO.setCardId(id);
        return updatedCardDTO;

    }

    public CardDTO move(int id, MoveCardDTO moveCardDTO, String userAgent) {
        Card moveTargetCard = findOne(id);
        moveTargetCard.changeSection(moveCardDTO.getSection());
        moveTargetCard.changeOrder(moveCardDTO.getPreOrder(), moveCardDTO.getNextOrder());

        cardRepository.move(moveTargetCard);
        CardDTO moveTargetCardDTO = new CardDTO(moveTargetCard.getOrder(), moveTargetCard.getTitle(), moveTargetCard.getContent(),
            moveTargetCard.getSectionType(), userAgent);
        moveTargetCardDTO.setCardId(id);
        return moveTargetCardDTO;
    }

    public CardDTO delete(int id) {
        int deletedId = cardRepository.delete(id);
        Card card = cardRepository.findById(deletedId)
            .orElseThrow(() -> new IllegalStateException("삭제를 위한 카드가 존재하지 않습니다."));
        CardDTO cardDto = new CardDTO(card.getOrder(), card.getTitle(), card.getContent(), card.getSectionType(), card.getAuthor());
        cardDto.setCardId(deletedId);
        return cardDto;
    }

    public ClassifiedCardsDTO findCards() {
        List<Card> cards = cardRepository.findAll();
        ClassifiedCardsDTO classifiedCardsDTO = classifyBySection(cards);
        classifiedCardsDTO.sort();
		return classifiedCardsDTO;
    }

    private ClassifiedCardsDTO classifyBySection(List<Card> cards) {
        ClassifiedCardsDTO classifiedCards = new ClassifiedCardsDTO();
        List<CardDTO> sectionCards;
        CardDTO cardDto;
        for (Card card : cards) {
            sectionCards = classifiedCards.get(card.getSectionType());
            cardDto = new CardDTO(card.getOrder(), card.getTitle(), card.getContent(), card.getSectionType(), card.getAuthor());
            cardDto.setCardId(card.getId());
            sectionCards.add(cardDto);
        }
        return classifiedCards;
    }

    public void batchProcess() {
        List<Card> todoCards = cardRepository.findBySection(Section.TODO);
        List<Card> doingCards = cardRepository.findBySection(Section.DOING);
        List<Card> doneCards = cardRepository.findBySection(Section.DONE);
        sortByOrder(todoCards);
        sortByOrder(doingCards);
        sortByOrder(doneCards);
    }

    private void sortByOrder(List<Card> cards) {
        int order = INCREMENT;
        for (Card card : cards) {
            card.setOrder(order);
            cardRepository.save(card);
            order+=INCREMENT;
        }
    }
}
