package team07.todolist.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import team07.todolist.domain.ActivityLog;
import team07.todolist.domain.Card;
import team07.todolist.dto.PatchCard;
import team07.todolist.dto.RequestCard;
import team07.todolist.dto.ResponseActivityLog;
import team07.todolist.dto.ResponseCard;
import team07.todolist.domain.Type;
import team07.todolist.repository.ActivityLogRepository;

@Service
public class ActivityLogService {

	private final ActivityLogRepository activityLogRepository;

	public ActivityLogService(ActivityLogRepository activityLogRepository) {
		this.activityLogRepository = activityLogRepository;
	}

	public void saveLog(RequestCard requestCard) {
		Type type = Type.ADD;
		ActivityLog activityLog = new ActivityLog(requestCard.getTitle(), type.getTypeValue(),
			Optional.empty(), Optional.of(requestCard.getStatus()));
		activityLogRepository.save(activityLog);
	}

	public void deleteLog(Card card) {
		Type type = Type.REMOVE;
		ResponseCard responseCard = card.createResponseCard();
		ActivityLog activityLog = new ActivityLog(responseCard.getTitle(), type.getTypeValue(),
			Optional.of(responseCard.getStatus()), Optional.empty());
		activityLogRepository.save(activityLog);
	}

	public void dragAndDropLog(Card card, RequestCard requestCard) {
		Type type = Type.MOVE;
		ResponseCard responseCard = card.createResponseCard();
		ActivityLog activityLog = new ActivityLog(requestCard.getTitle(), type.getTypeValue(),
			Optional.of(responseCard.getStatus()), Optional.of(requestCard.getStatus()));
		activityLogRepository.save(activityLog);
	}

	public void changeTextLog(Card card, PatchCard patchCard) {
		Type type = Type.UPDATE;
		ResponseCard responseCard = card.createResponseCard();
		Integer status = responseCard.getStatus();
		ActivityLog activityLog = new ActivityLog(patchCard.getTitle(), type.getTypeValue(),
			Optional.of(status), Optional.of(status));
		activityLogRepository.save(activityLog);
	}

	public List<ResponseActivityLog> findAll() {
		return activityLogRepository.findAll().stream()
			.map(ActivityLog::createResponseActivityLog)
			.collect(Collectors.toList());
	}

	public void reset() {
		activityLogRepository.reset();
	}
}
