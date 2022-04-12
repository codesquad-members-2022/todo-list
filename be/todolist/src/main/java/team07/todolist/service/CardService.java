package team07.todolist.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import team07.todolist.domain.Card;
import team07.todolist.dto.PatchCard;
import team07.todolist.dto.RequestCard;
import team07.todolist.dto.ResponseCard;
import team07.todolist.repository.CardRepository;

@Service
public class CardService {

	private CardRepository cardRepository;
	private ActivityLogService activityLogService;

	public CardService(CardRepository cardRepository, ActivityLogService activityLogService) {
		this.cardRepository = cardRepository;
		this.activityLogService = activityLogService;
	}

	public void save(RequestCard requestCard) {
		Card newCard = new Card(requestCard.getUserId(), requestCard.getTitle(),
			requestCard.getContent(), requestCard.getSequence(), requestCard.getStatus());

		int status = requestCard.getStatus();
		cardRepository.save(newCard);
		activityLogService.saveLog(requestCard);
	}

	public Card delete(Long id) {
		activityLogService.deleteLog(cardRepository.findById(id));

		return cardRepository.delete(id);
	}

	public ResponseCard dragAndDrop(Long id, RequestCard requestCard) {
		Card originCard = cardRepository.findById(id);
		Card updateCard = cardRepository.updateStatusAndRow(id, requestCard);

		activityLogService.dragAndDropLog(originCard, requestCard);
		return updateCard.createResponseCard();
	}

	public ResponseCard changeText(Long id, PatchCard patchCard) {

		Card originCard = cardRepository.findById(id);

		Card card = new Card(originCard, patchCard.getTitle(), patchCard.getContent());

		Card updateCard = cardRepository.updateText(id, card);

		activityLogService.changeTextLog(originCard, patchCard);

		return updateCard.createResponseCard();
	}

	public List<ResponseCard> findAll() {
		return cardRepository.findAll()
			.stream().filter(Card::isValid)
			.map(Card::createResponseCard)
			.collect(Collectors.toList());
	}

	public void reset() {
		cardRepository.reset();
	}
}
