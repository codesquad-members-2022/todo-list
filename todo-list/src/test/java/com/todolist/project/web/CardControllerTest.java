package com.todolist.project.web;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todolist.project.service.CardService;
import com.todolist.project.web.dto.CardAddDto;
import com.todolist.project.web.dto.CardListRequestDto;
import com.todolist.project.web.dto.CardUpdateDto;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(CardController.class)
class CardControllerTest {

	@Autowired
	MockMvc mockMvc;
	@Autowired
	ObjectMapper objectMapper;

	@MockBean
	CardService service;

	CardAddDto addDto;
	CardUpdateDto updateDto;
	CardListRequestDto listDto;

	@BeforeEach
	void setUp() {
		updateDto = new CardUpdateDto(1, "title1", "content1", "TODO");
		listDto = new CardListRequestDto(1L, 1, "title1", "content1", "writer", "TODO",
			LocalDateTime.now());
		addDto = new CardAddDto(1, "title1", "content1", "writer", "TODO");
	}

	@Test
	void list() throws Exception {
//		List<CardListRequestDto> list = List.of(this.listDto);
//		given(service.findAll())
//			.willReturn(list);

		ResultActions actions = mockMvc.perform(get("/cards").accept(MediaType.APPLICATION_JSON));

		actions.andExpect(status().isOk());
		//TODO: content().contentType(MediaType.APPLICATION_JSON) -> cast하면 에러나서 해결을 해야한다.
	}

	@Test
	void listByStatus() {
		//TODO: 테스트 작성하기
	}

	@Test
	void add() throws Exception {
		ResultActions actions = mockMvc.perform(post("/cards")
			.contentType("application/json")
			.content(objectMapper.writeValueAsString(addDto)));

		actions.andExpect(status().is2xxSuccessful());

		then(service).should(times(1)).addCard(any(CardAddDto.class));
	}

	@Test
	void remove() {
		//TODO: 테스트 작성하기
	}

	@Test
	void update() {
		//TODO: 테스트 작성하기
	}
}
