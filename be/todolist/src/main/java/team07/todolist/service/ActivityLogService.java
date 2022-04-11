package team07.todolist.service;

import java.util.List;
import org.springframework.stereotype.Service;
import team07.todolist.domain.ActivityLog;
import team07.todolist.domain.Card;
import team07.todolist.dto.PatchCard;
import team07.todolist.dto.RequestCard;
import team07.todolist.dto.ResponseCard;
import team07.todolist.repository.ActivityLogRepository;

@Service
public class ActivityLogService {

	private Type type;

	private final ActivityLogRepository activityLogRepository;

	public ActivityLogService(ActivityLogRepository activityLogRepository) {
		this.activityLogRepository = activityLogRepository;
	}

	public void saveLog(RequestCard requestCard) {
		type = Type.ADD;
		ActivityLog activityLog = new ActivityLog(requestCard.getTitle(), type.getTypeValue(), null, requestCard.getStatus());
		activityLogRepository.save(activityLog);
	}

	public void deleteLog(Card card) {
		type = Type.REMOVE;
		ResponseCard responseCard = card.createResponseCard();
		ActivityLog activityLog = new ActivityLog(responseCard.getTitle(), type.getTypeValue(), responseCard.getStatus(), null);
		activityLogRepository.save(activityLog);
	}

	public void dragAndDropLog(Card card, RequestCard requestCard) {
		type = Type.MOVE;
		ResponseCard responseCard = card.createResponseCard();
		ActivityLog activityLog = new ActivityLog(requestCard.getTitle(), type.getTypeValue(),
			responseCard.getStatus(), requestCard.getStatus());
		activityLogRepository.save(activityLog);
	}

	public void changeTextLog(Card card, PatchCard patchCard) {
		type = Type.UPDATE;
		ResponseCard responseCard = card.createResponseCard();
		Integer status = responseCard.getStatus();
		ActivityLog activityLog = new ActivityLog(patchCard.getTitle(), type.getTypeValue(), status, status);
		activityLogRepository.save(activityLog);
	}

	public List<ActivityLog> findAll() {
		return activityLogRepository.findAll();
	}
}
