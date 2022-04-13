package team07.todolist.service;

import org.springframework.stereotype.Service;
import team07.todolist.domain.ActivityLog;
import team07.todolist.domain.Card;
import team07.todolist.dto.PatchCard;
import team07.todolist.dto.RequestCard;
import team07.todolist.dto.ResponseCard;
import team07.todolist.repository.ActivityLogRepository;

@Service
public class ActivityLogService {

	private static final int ADD = 1;
	private static final int REMOVE = 2;
	private static final int UPDATE = 3;
	private static final int MOVE = 4;

	private ActivityLogRepository activityLogRepository;

	public ActivityLogService(ActivityLogRepository activityLogRepository) {
		this.activityLogRepository = activityLogRepository;
	}

	public void saveLog(RequestCard requestCard) {
		ActivityLog activityLog = new ActivityLog(requestCard.getTitle(), ADD, null, requestCard.getStatus());
		activityLogRepository.save(activityLog);
	}

	public void deleteLog(Card card) {
		ResponseCard responseCard = card.createResponseCard();
		ActivityLog activityLog = new ActivityLog(responseCard.getTitle(), REMOVE, responseCard.getStatus(), null);
		activityLogRepository.save(activityLog);
	}

	public void dragAndDropLog(Card card, RequestCard requestCard) {
		ResponseCard responseCard = card.createResponseCard();
		ActivityLog activityLog = new ActivityLog(requestCard.getTitle(), MOVE,
			responseCard.getStatus(), requestCard.getStatus());
		activityLogRepository.save(activityLog);
	}

	public void changeTextLog(Card card, PatchCard patchCard) {
		ResponseCard responseCard = card.createResponseCard();
		Integer status = responseCard.getStatus();
		ActivityLog activityLog = new ActivityLog(patchCard.getTitle(), UPDATE, status, status);
		activityLogRepository.save(activityLog);
	}

}
