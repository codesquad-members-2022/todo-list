package com.team05.todolist.controller;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.team05.todolist.domain.Event;
import com.team05.todolist.domain.dto.CardDTO;
import com.team05.todolist.domain.dto.CreateCardDTO;
import com.team05.todolist.domain.dto.LogDTO;
import com.team05.todolist.domain.dto.ResponseDTO;
import com.team05.todolist.service.CardService;
import com.team05.todolist.service.LogService;
import java.time.LocalDateTime;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(CardController.class)
@ActiveProfiles("mysql")
public class CardControllerTest {

	@Autowired
	private MockMvc mockMvc;

	ObjectMapper mapper = new ObjectMapper();

	@MockBean
	private CardService cardService;

	@MockBean
	private LogService logService;

	@Test
	void 카드_등록() throws Exception {
		mapper.registerModule(new JavaTimeModule());
		final CreateCardDTO createCardDTO =new CreateCardDTO(0, "제목1", "내용1", "todo");
		final CardDTO cardDTO = new CardDTO(1000, "제목1", "내용1", "todo");
		final LogDTO log = new LogDTO(1, "create", LocalDateTime.now(), "로그1", "todo");

		given(cardService.save(any(CreateCardDTO.class))).willReturn(cardDTO);
		given(logService.save(Event.CREATE, createCardDTO.getTitle(), createCardDTO.getSection())).willReturn(log);

		mockMvc.perform(MockMvcRequestBuilders
			.post("/api/cards").header("user-agent", "android")
			.content(mapper.writeValueAsString(new ResponseDTO(cardDTO, log, "android")))
			.contentType(APPLICATION_JSON)
			.accept(APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.card").value(IsNull.nullValue()));
	}
}
