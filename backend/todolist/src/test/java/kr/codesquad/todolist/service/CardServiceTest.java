package kr.codesquad.todolist.service;

import kr.codesquad.todolist.controller.request.CardRequestDto;
import kr.codesquad.todolist.controller.response.CardResponseDto;
import kr.codesquad.todolist.dao.CardDao;
import kr.codesquad.todolist.domain.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CardServiceTest {
	public static final String CARD_TEST_SUBJECT = "테스트 제목";
	public static final String CARD_TEST_CONTENT = "테스트 내용";
	public static final Card.TodoStatus CARD_TEST_STATUS = Card.TodoStatus.ONGOING;
	public static final long CARD_TEST_USER_ID = 1L;

	@InjectMocks
	private CardService cardService;

	@Mock
	private CardDao cardDao;

	@DisplayName("카드 등록 요청시 전달받은 카드정보를 DB에 저장되고, 생성된 card-id를 확인한다.")
	@Test
	void create_card_ok() {
		CardRequestDto.WriteRequest expected = getWriteRequestDto();
		when(cardDao.save(any()))
			.thenReturn(getCard());

		CardResponseDto.Redirection actual = cardService.create(expected);

		assertThat(actual.getCardId()).isNotZero();
	}


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

		CardResponseDto.CardResponse actual = cardService.readFrom(CARD_TEST_USER_ID);

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

		assertThrows(IllegalArgumentException.class, () -> cardService.readFrom(99999L));
	}

	@DisplayName("사용자별 전체 카드 조회 요청시 DB 로부터 사용자에 해당하는 각 status 별 card 개수, 카드목록을 확인한다.")
	@Test
	void read_all_cards() {
		Card expectedCard = getCard();
		when(cardDao.findByUserIdAndTodoStatus(anyLong(), any()))
			.thenReturn(List.of(expectedCard));
		when(cardDao.findGroupByTodoStatus(anyLong()))
			.thenReturn(List.of(getCardStatusNumber()));

		CardResponseDto.CardsResponse actual = cardService.readAllFrom(CARD_TEST_USER_ID);

		assertAll(
			() -> assertThat(actual.getData().get(CARD_TEST_STATUS.getText()).getCount()).isNotZero(),
			() -> assertThat(actual.getData().get(CARD_TEST_STATUS.getText()).getCards().size()).isNotZero(),
			() -> assertThat(actual.getData().get(CARD_TEST_STATUS.getText()).getCards().get(0).getStatus()).isEqualTo(CARD_TEST_STATUS.getText()),
			() -> assertThat(actual.getData().get(CARD_TEST_STATUS.getText()).getCards().get(0).getUserId()).isEqualTo(CARD_TEST_USER_ID)
		);
	}

	private Card getCard() {
		return new Card(CARD_TEST_USER_ID, CARD_TEST_SUBJECT, CARD_TEST_CONTENT, CARD_TEST_STATUS,1L, false, LocalDateTime.now(),
			CARD_TEST_USER_ID);
	}

	private CardRequestDto.WriteRequest getWriteRequestDto() {
		CardRequestDto.WriteRequest request = new CardRequestDto.WriteRequest();
		request.setSubject(CARD_TEST_SUBJECT);
		request.setContent(CARD_TEST_CONTENT);
		request.setStatus(CARD_TEST_STATUS.getText());
		request.setUserId(CARD_TEST_USER_ID);
		return request;
	}

	private CardStatusNumber getCardStatusNumber() {
		return new CardStatusNumber(CARD_TEST_STATUS, 1L);
	}
}
