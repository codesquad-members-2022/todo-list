package com.team05.todolist.service;

import com.team05.todolist.domain.Card;
import com.team05.todolist.domain.dto.CardDTO;
import com.team05.todolist.repository.CardRepository;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    private static final Integer NON_DELETED = 0;

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public void save(CardDTO cardDto) {
        Card card = new Card(cardDto.getOrderIndex(), NON_DELETED, cardDto.getTitle(), cardDto.getContent(),
            cardDto.getSection());

        cardRepository.save(card);

    }

//    public List<Card> findCards() {
//        return cardRepository.findAll();
//    }

//    public List<Log> findLogs() {
//        return logRepository.findAll();
//    }

//    public ResponseCardDto findOne(int id) throws NoSuchElementException {
//        Optional<Card> card = cardRepository.findById(id);
//        return card.orElseThrow();
//    }
//
//    public void update(RequestCardDto updateCardDto) {
//        Card updateTargetCard = findOne(updateCardDto.getId());
//        updateTargetCard.changeSection(updateCardDto.getSection());
//        updateTargetCard.changeTitle(updateCardDto.getTitle());
//        updateTargetCard.changeContent(updateCardDto.getContent());
//        updateTargetCard.changeOrderIndex(updateCardDto.getOrderIndex());
//
//        cardRepository.save(updateTargetCard);
//    }
//
//    public void delete(RequestCardDto requestCardDto) {
//
//    }
}
