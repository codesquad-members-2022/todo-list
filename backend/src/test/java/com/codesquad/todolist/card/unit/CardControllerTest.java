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
import com.codesquad.todolist.card.CardService;

@WebMvcTest(CardController.class)
public class CardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CardService cardService;

    @Test
    @DisplayName("카드를 생성하는 요청이 오면 카드가 생성된다")
    public void createCardTest() throws Exception {

        // when
        ResultActions actions = mockMvc.perform(post("/cards")
            .content(
                "{" +
                    "\"columnId\": 1,"
                    + "\"title\": \"HTML/CSS 공부하기\","
                    + "\"content\": \"매주 1시간 동안 HTML/CSS 책 읽기\","
                    + "\"author\": \"web\""
                    + "}"
            ));

        // then
        actions.andExpect(status().is2xxSuccessful());
    }
}
