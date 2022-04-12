package com.codesquad.todolist.card.unit;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
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
import com.codesquad.todolist.card.CardService;
import com.codesquad.todolist.card.dto.CardCreateRequest;
import com.codesquad.todolist.card.dto.CardMoveRequest;
import com.codesquad.todolist.card.dto.CardUpdateRequest;
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
        // given
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

    @Test
    @DisplayName("카드 삭제를 요청하면 204 NO CONTENT 를 응답으로 받게 된다")
    public void cardDeleteTest() throws Exception {

        // when
        ResultActions actions = mockMvc.perform(delete("/cards/{id}", 1)
            .contentType("application/json")
            .content(objectMapper.writeValueAsString("cardId:1"))
        );

        // then
        actions.andExpect(status().isNoContent());

        then(cardService).should(times(1)).delete(anyInt());

    }

    @Test
    @DisplayName("카드 이동을 요청하면 201 CREATED 를 응답으로 받게 된다.")
    public void cardMoveTest() throws Exception {
        // given
        CardMoveRequest request = new CardMoveRequest(1, 3);

        // when
        ResultActions actions = mockMvc.perform(put("/cards/{id}/move", 1)
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(request)));

        // then
        actions.andExpect(status().isCreated());
        then(cardService).should(times(1)).move(anyInt(), any(CardMoveRequest.class));
    }
}
