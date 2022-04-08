package kr.codesquad.todolist.card;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CardService {
	private final CardDao cardDao;

	@Transactional
	public CardDto.RedirectInfo createCard(CardDto.WriteRequest request) {
		Card card = request.toEntity();
		Card cardInfo = cardDao.save(card);
		return new CardDto.RedirectInfo(cardInfo.getTodoId(), cardInfo.getUserId());
	}

	public CardDto.WriteResponse readOf(Long id, Long userId) {
		String errorMessage = String.format("error of card id: %dor user id: %d", id, userId);
		Card cardInfo = cardDao.findByIdAndUserId(id, userId)
			.orElseThrow(() -> new IllegalArgumentException(errorMessage));
		return new CardDto.WriteResponse(cardInfo);
	}
}
