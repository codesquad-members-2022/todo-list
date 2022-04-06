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
		int status = requestCard.getStatus();
		cardRepository.save(newCard, status);
	}

	public Card delete(Long id) {
		return cardRepository.delete(id);
	}

	public Card changeRow(Long id, RequestCard requestCard) {
		Card card = cardRepository.findById(id);

		requestCard;

		//만약 status만 다르면
		// updateStatus를 실행하고 return한다.
		1;
		cardRepository.updateStatus(id);

		//만약 row만 다르면
		// updateRow를 실행한다.
		2;
		cardRepository.updateRow(id);

		//todo 비교
		// 만약 status가 다르다면 -> 왼쪽 오른쪽으로 드래그 앤 드랍 progress -> done    update status를 실행시킨다.
		// 만약 row만 다르다면 -> progress -> progress로 이동한 경우 (row가 낮은 곳에서 높은 곳으로 이동 // row가 높은 곳에서 낮은 곳으로 이동)     2

		return null;
	}

	public Card changeText(Long id, RequestCard requestCard) {

		//todo
		// title or content가 바뀌었다면 드래그 앤 드랍이 아닌 내용만 수정한 상황    3

		3;
		cardRepository.updateText(id);

		return null;
	}

	public List<ResponseCard> findAll() {
		return cardRepository.findAll()
			.stream().filter(Card::isValid)
			.map(Card::createResponseCard)
			.collect(Collectors.toList());
	}

}
