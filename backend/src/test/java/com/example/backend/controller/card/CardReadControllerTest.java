package com.example.backend.controller.card;

import com.example.backend.controller.card.dto.Author;
import com.example.backend.controller.card.dto.DailyPlanResponse;
import com.example.backend.controller.card.dto.Task;
import com.example.backend.domain.card.CardType;
import com.example.backend.service.card.CardReadService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;
import java.util.Map;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CardReadController.class)
class CardReadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CardReadService cardReadService;

    private DailyPlanResponse dailyPlanResponse;

    private DailyPlanResponse getDailyPlanResponse() {
        return new DailyPlanResponse();
    }


    @Test
    @DisplayName("")
    void m() throws Exception {
        given(cardReadService.getDailyPlan())
                .willReturn(getDailyPlanResponse());

        ResultActions resultAction = mockMvc.perform(post("/api/read/cards"));

        resultAction.andExpect(status().isOk())
                .andReturn();
    }
}
