package kr.codesquad.todolist.card;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CardService {
	private final CardDao cardDao;

	public CardDto.WriteResponse createCard(CardDto.WriteRequest request) {
		Card card = request.toEntity();
		return new CardDto.WriteResponse(cardDao.save(card));
	}
}
