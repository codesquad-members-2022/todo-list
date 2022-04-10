package kr.codesquad.todolist.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CardServiceTest {
	@InjectMocks
	private CardService cardService;

	@Mock
	private CardDao cardDao;

	/**
	 * cardId 조회 결과 확인 내용
	 * - cardId 일치여부
	 * - 로그인 사용자와 카드 이용자 일치여부
	 * - client 상에서 카드 위치 정보
	 *   - todoStatus
	 *   - order
	 */
	@DisplayName("사용자의 카드조회 요청시 DB를 통한 조회 결과를 확인한다.")
	@Test
	void read_a_card_from_user() {
		Card expected = getCard();
		when(cardDao.findById(anyLong()))
			.thenReturn(Optional.of(expected));

		CardDto.WriteResponse actual = cardService.readOf(1L);

		assertAll(
			() -> assertThat(actual.getCardId()).isEqualTo(expected.getCardId()),
			() -> assertThat(actual.getUserId()).isEqualTo(expected.getUserId()),
			() -> assertThat(actual.getStatus()).isEqualTo(expected.getStatus().getText()),
			() -> assertThat(actual.getOrder()).isEqualTo(expected.getOrder())
		);
	}

	@DisplayName("사용자의 카드조회 요청시 존재하지 않는 card-id 정보에 대한 예외처리를 확인한다.")
	@Test
	void read_a_card_with_not_existed_card_id() {
		Mockito.lenient().when(cardDao.findById(anyLong()))
			.thenReturn(Optional.empty());

		assertThrows(IllegalArgumentException.class, () -> cardService.readOf(99999L));
	}

	private Card getCard() {
		return new Card(1L, "테스트 제목", "테스트 내용", Card.TodoStatus.ONGOING, 1L, false, LocalDateTime.now(), 1L);
	}
}
