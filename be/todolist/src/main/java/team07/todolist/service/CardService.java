package team07.todolist.service;

import java.util.List;
import org.springframework.stereotype.Service;
import team07.todolist.domain.Card;
import team07.todolist.dto.ResponseCard;
import team07.todolist.repository.CardRepository;

@Service
public class CardService {

	private CardRepository cardRepository;

	public CardService(CardRepository cardRepository) {
		this.cardRepository = cardRepository;
	}

	public void save(ResponseCard responseCard) {
		//todo
		//Card card responseCard이용해서 만들기
//		cardRepository.save(card);

	}

	public void delete(int row, int status) {

	}

	public Card changeRow(int row, int status) {
		return null;
	}


}
