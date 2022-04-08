package kr.codesquad.todolist.card;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CardService {
	private final CardDao cardDao;

	public CardDto.RedirectInfo createCard(CardDto.WriteRequest request) {
		Card card = request.toEntity();
		Card cardInfo = cardDao.save(card);
		return new CardDto.RedirectInfo(cardInfo.getTodoId(), cardInfo.getUserId());
	}

	public CardDto.WriteResponse readOf(Long id, Long userId) {
		Card cardInfo = cardDao.findByIdAndUserId(id, userId)
			.orElseThrow(() -> new IllegalArgumentException("card id 해당 정보 없습니다."));
		return new CardDto.WriteResponse(cardInfo);
	}
}
