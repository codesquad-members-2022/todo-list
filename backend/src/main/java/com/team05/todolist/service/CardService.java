package com.team05.todolist.service;

import com.team05.todolist.controller.RequestCardDto;
import com.team05.todolist.controller.ResponseCardDto;
import com.team05.todolist.domain.Card;
import com.team05.todolist.domain.Log;
import com.team05.todolist.repository.CardRepository;
import com.team05.todolist.repository.LogRepository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    private final CardRepository cardRepository;
    private final LogRepository logRepository;

    public CardService(CardRepository cardRepository, LogRepository logRepository) {
        this.cardRepository = cardRepository;
        this.logRepository = logRepository;
    }

    public List<Card> findCards() {
        return cardRepository.findAll();
    }

    public List<Log> findLogs() {
        return logRepository.findAll();
    }

    public ResponseCardDto findOne(int id) throws NoSuchElementException {
        Optional<Card> card = cardRepository.findById(id);
        return card.orElseThrow();
    }

    public void update(RequestCardDto updateCardDto) {
        Card updateTargetCard = findOne(updateCardDto.getId());
        updateTargetCard.changeSection(updateCardDto.getSection());
        updateTargetCard.changeTitle(updateCardDto.getTitle());
        updateTargetCard.changeContent(updateCardDto.getContent());
        updateTargetCard.changeOrderIndex(updateCardDto.getOrderIndex());

        cardRepository.save(updateTargetCard);
    }

    public void delete(RequestCardDto requestCardDto) {

    }
}
