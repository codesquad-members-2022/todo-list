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
@Transactional
public class CardService {

    private final CardRepository cardRepository;
    private final ActivityLogService activityLogService;

    public CardService(CardRepository cardRepository, ActivityLogService activityLogService) {
        this.cardRepository = cardRepository;
        this.activityLogService = activityLogService;
    }

    public CardCommandResponse save(CardSaveRequest cardSaveRequest) {
        Card card = cardSaveRequest.toEntity();
        Card savedCard = cardRepository.save(card);

        ActivityLog activityLog = new ActivityLog(Action.ADD, card.getTitle(), card.getStatus());
        activityLogService.save(activityLog);

        return new CardCommandResponse(savedCard);
    }

    @Transactional(readOnly = true)
    public CardCollectionResponse findCollections() {
        List<Card> todoCards = cardRepository.findSameStatusOrderByUpdateDatetimeDesc(CardStatus.TODO);
        List<Card> inProgressCards = cardRepository.findSameStatusOrderByUpdateDatetimeDesc(CardStatus.IN_PROGRESS);
        List<Card> doneCards = cardRepository.findSameStatusOrderByUpdateDatetimeDesc(CardStatus.DONE);

        return new CardCollectionResponse(todoCards, inProgressCards, doneCards);
    }

    public CardCommandResponse modify(CardModifyRequest cardModifyRequest) {
        Card card = cardModifyRequest.toEntity();
        cardRepository.update(card);
        Card foundCard = cardRepository.findById(card.getId());

        ActivityLog activityLog = new ActivityLog(Action.UPDATE, foundCard.getTitle(), foundCard.getStatus());
        activityLogService.save(activityLog);

        return new CardCommandResponse(foundCard);
    }

    public CardCommandResponse delete(Long cardId) {
        Card foundCard = cardRepository.findById(cardId);
        cardRepository.delete(cardId);

        ActivityLog activityLog = new ActivityLog(Action.REMOVE, foundCard.getTitle(), foundCard.getStatus());
        activityLogService.save(activityLog);

        return new CardCommandResponse(foundCard);
    }

    public CardCommandResponse move(Long cardId, CardMoveRequest cardMoveRequest) {
        Card card = new Card(cardId, cardMoveRequest.getNowStatus());
        cardRepository.move(card);

        Card foundCard = cardRepository.findById(cardId);

        ActivityLog activityLog = new ActivityLog(Action.MOVE, foundCard.getTitle(), cardMoveRequest.getNowStatus(), cardMoveRequest.getBeforeStatus());
        activityLogService.save(activityLog);

        return new CardCommandResponse(foundCard);
    }
}
