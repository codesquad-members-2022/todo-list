package com.codesquad.todolist.card.unit;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.codesquad.todolist.card.CardController;
import com.codesquad.todolist.card.CardCreateRequest;
import com.codesquad.todolist.card.CardService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CardController.class)
public class CardControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CardService cardService;

    @Test
    @DisplayName("카드를 생성하는 요청이 오면 카드가 생성된다")
    public void cardCreateTest() throws Exception {

        CardCreateRequest request = new CardCreateRequest(1, "제목", "작성자", "내용");

        // when
        ResultActions actions = mockMvc.perform(post("/cards")
            .contentType("application.json")
            .content(objectMapper.writeValueAsString(request)));

        // then
        actions.andExpect(status().is2xxSuccessful());
    }
}
