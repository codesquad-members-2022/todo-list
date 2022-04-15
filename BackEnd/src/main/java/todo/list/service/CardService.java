package todo.list.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import todo.list.domain.Action;
import todo.list.domain.ActivityLog;
import todo.list.service.dto.*;
import todo.list.domain.Card;
import todo.list.domain.CardStatus;
import todo.list.repository.CardRepository;

import java.util.List;

@Service
public class CardService {

    private final CardRepository cardRepository;
    private final ActivityLogService activityLogService;

    public CardService(CardRepository cardRepository, ActivityLogService activityLogService) {
        this.cardRepository = cardRepository;
        this.activityLogService = activityLogService;
    }

    @Transactional
    public CardCommandResponse save(CardSaveRequest cardSaveRequest) {
        Card card = cardSaveRequest.toEntity();
        Card savedCard = cardRepository.save(card);

        ActivityLog activityLog = new ActivityLog(Action.ADD, card.getTitle(), card.getStatus());
        activityLogService.save(activityLog);

        return new CardCommandResponse(savedCard);
    }

    @Transactional(readOnly = true)
    public CardCollectionResponse findCollections() {
        List<Card> todoCards = cardRepository.findAllSameStatus(CardStatus.TODO);
        List<Card> inProgressCards = cardRepository.findAllSameStatus(CardStatus.IN_PROGRESS);
        List<Card> doneCards = cardRepository.findAllSameStatus(CardStatus.DONE);

        return new CardCollectionResponse(todoCards, inProgressCards, doneCards);
    }

    @Transactional
    public CardCommandResponse modify(CardModifyRequest cardModifyRequest) {
        Card card = cardModifyRequest.toEntity();
        cardRepository.update(card);
        Card foundCard = cardRepository.findById(card.getId());

        ActivityLog activityLog = new ActivityLog(Action.UPDATE, foundCard.getTitle(), foundCard.getStatus());
        activityLogService.save(activityLog);

        return new CardCommandResponse(foundCard);
    }

    @Transactional
    public CardCommandResponse delete(Long cardId) {
        Card foundCard = cardRepository.findById(cardId);
        cardRepository.delete(cardId);

        ActivityLog activityLog = new ActivityLog(Action.REMOVE, foundCard.getTitle(), foundCard.getStatus());
        activityLogService.save(activityLog);

        return new CardCommandResponse(foundCard);
    }
}
