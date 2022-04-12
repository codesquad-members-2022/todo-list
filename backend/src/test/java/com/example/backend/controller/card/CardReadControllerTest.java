package com.example.backend.controller.card;

import com.example.backend.service.card.CardReadService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;

@WebMvcTest(CardReadController.class)
class CardReadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CardReadService cardReadService;

    @Test
    @DisplayName("")
    void m() throws Exception {
        given(cardReadService.getDailyPlan());
//                .willReturn()
    }
}
