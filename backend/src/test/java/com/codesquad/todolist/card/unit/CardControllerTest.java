package com.codesquad.todolist.card.unit;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.codesquad.todolist.card.CardController;
import com.codesquad.todolist.card.CardService;
import com.codesquad.todolist.card.dto.CardCreateRequest;
import com.codesquad.todolist.card.dto.CardUpdateRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

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
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(request)));

        // then
        actions.andExpect(status().is2xxSuccessful());

        then(cardService).should(times(1)).create(any(CardCreateRequest.class));
    }

    @Test
    @DisplayName("카드 업데이트를 요청하면 200 OK 를 응답으로 받게 된다")
    public void cardUpdateTest() throws Exception {
        // given
        CardUpdateRequest request = new CardUpdateRequest("제목", "내용", "작성자");

        // when
        ResultActions actions = mockMvc.perform(put("/cards/{id}", 1)
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(request))
        );

        // then
        actions.andExpect(status().isOk());

        then(cardService).should(times(1)).update(anyInt(), any(CardUpdateRequest.class));
    }
}
