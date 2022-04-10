package kr.codesquad.todolist.card;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CardService {
	public static final String ERROR_OF_CARD_ID = "error of card id: %d";

	private final CardDao cardDao;

	@Transactional
	public CardDto.Redirection createCard(CardDto.WriteRequest request) {
		Card card = request.toEntity();
		Card cardInfo = cardDao.save(card);
		return new CardDto.Redirection(cardInfo.getCardId(), cardInfo.getUserId());
	}

	public CardDto.WriteResponse readOf(Long id) {
		String errorMessage = String.format(ERROR_OF_CARD_ID, id);
		Card cardInfo = cardDao.findById(id)
			.orElseThrow(() -> new IllegalArgumentException(errorMessage));
		return new CardDto.WriteResponse(cardInfo);
	}
}
