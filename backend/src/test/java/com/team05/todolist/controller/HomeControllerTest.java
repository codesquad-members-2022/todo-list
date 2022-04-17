package com.team05.todolist.controller;


import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;

import com.team05.todolist.domain.dto.CardDTO;
import com.team05.todolist.domain.dto.ClassifiedCardsDTO;
import com.team05.todolist.domain.dto.ListResponseDTO;
import com.team05.todolist.domain.dto.LogDTO;
import com.team05.todolist.service.CardService;
import com.team05.todolist.service.LogService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class HomeControllerTest {

	@Mock
	private CardService cardService;
	@Mock
	private LogService logService;

	@InjectMocks
	private HomeController homeController;

	@Test
	void 전체_카드_및_로그_조회() {
		final CardDTO card1 = new CardDTO(1000, "제목1", "내용1", "todo", "android");
		final CardDTO card2 = new CardDTO(2000, "제목2", "내용2", "todo", "android");
		final CardDTO card3 = new CardDTO(1000, "제목3", "내용3", "doing", "android");
		final CardDTO card4 = new CardDTO(1000, "제목4", "내용4", "done", "android");

		final ClassifiedCardsDTO classifiedCards = new ClassifiedCardsDTO();
		classifiedCards.get("todo").add(card1);
		classifiedCards.get("todo").add(card2);
		classifiedCards.get("doing").add(card3);
		classifiedCards.get("done").add(card4);

		final LogDTO log1 = new LogDTO(1, "create", LocalDateTime.now(), "로그1", "todo");
		final LogDTO log2 = new LogDTO(2, "create", LocalDateTime.now(), "로그1", "doing");
		final LogDTO log3 = new LogDTO(3, "create", LocalDateTime.now(), "로그1", "done");

		final List<LogDTO> logs = new ArrayList<>();
		logs.add(log1);
		logs.add(log2);
		logs.add(log3);

		given(cardService.findCards())
			.willReturn(classifiedCards);

		given(logService.findLogs(0, 10))
			.willReturn(logs);

		ResponseEntity<ListResponseDTO> responseEntity = homeController.inquireAll();
		assertThat(responseEntity).isNotNull();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

		ClassifiedCardsDTO returnClassifiedCardsDTO = Objects.requireNonNull(
			responseEntity.getBody()).getClassifiedCardsDTO();
		List<LogDTO> returnLogs = responseEntity.getBody().getLogs();

		assertThat(returnClassifiedCardsDTO.getClassifiedCards().get("todo")).hasSize(2);
		assertThat(returnClassifiedCardsDTO.getClassifiedCards().get("doing")).hasSize(1);
		assertThat(returnClassifiedCardsDTO.getClassifiedCards().get("done")).hasSize(1);
		assertThat(returnLogs).hasSize(3);

		assertThat(returnClassifiedCardsDTO.getClassifiedCards().get("todo").get(0)).isEqualTo(card1);
		assertThat(returnClassifiedCardsDTO.getClassifiedCards().get("todo").get(1)).isEqualTo(card2);
		assertThat(returnClassifiedCardsDTO.getClassifiedCards().get("doing").get(0)).isEqualTo(card3);
		assertThat(returnClassifiedCardsDTO.getClassifiedCards().get("done").get(0)).isEqualTo(card4);
	}

	@Test
	void batchProcess() {
	}

	@Test
	void logPaging() {
	}
}
