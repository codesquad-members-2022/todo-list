package team07.todolist.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import team07.todolist.domain.Card;
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
		Card newCard = new Card(requestCard.getUserId(), requestCard.getTitle(),
			requestCard.getContent(), requestCard.getRow(),
			requestCard.getStatus());
		int status = requestCard.getStatus().intValue();
		cardRepository.save(newCard, status);
	}

	public Card delete(Long id) {
		return cardRepository.delete(id);
	}

	public Card changeRow(int row, int status) {
		return null;
	}

	public List<ResponseCard> findAll() {
		return cardRepository.findAll()
			.stream().filter(Card::isValid)
			.map(Card::createResponseCard)
			.collect(Collectors.toList());
	}

}
