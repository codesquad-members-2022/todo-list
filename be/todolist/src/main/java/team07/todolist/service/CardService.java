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

	public CardService(CardRepository cardRepository) {
		this.cardRepository = cardRepository;
	}

	public void save(RequestCard requestCard) {
		Card newCard = new Card.Builder().userId(requestCard.getUserId())
			.title(requestCard.getTitle())
			.content(requestCard.getContent())
			.row(requestCard.getRow())
			.status(requestCard.getStatus())
			.build();

		int status = requestCard.getStatus();
		cardRepository.save(newCard, status);
	}

	public Card delete(Long id) {
		return cardRepository.delete(id);
	}

	public ResponseCard dragAndDropHorizon(Long id, RequestCard requestCard) {
		Card updateCard = cardRepository.updateStatusAndRow(id, requestCard.getRow(),
			requestCard.getStatus());

		return updateCard.createResponseCard();
	}

	public ResponseCard dragAndDropVertical(Long id, RequestCard requestCard) {

		Card changedCard = new Card.Builder(cardRepository.findById(id))
			.row(requestCard.getRow())
			.build();

		Card updateCard = cardRepository.updateRow(id, changedCard);
		return updateCard.createResponseCard();
	}

	public ResponseCard changeText(Long id, PatchCard patchCard) {
		Card card = new Card.Builder(cardRepository.findById(id))
			.title(patchCard.getTitle())
			.content(patchCard.getContent())
			.build();

		Card updateCard = cardRepository.updateText(id, card);

		return updateCard.createResponseCard();
	}

	public List<ResponseCard> findAll() {
		return cardRepository.findAll()
			.stream().filter(Card::isValid)
			.map(Card::createResponseCard)
			.collect(Collectors.toList());
	}

}
