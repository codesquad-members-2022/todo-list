package com.ijava.todolist.history.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ijava.todolist.history.Action;
import com.ijava.todolist.history.domain.History;
import com.ijava.todolist.history.service.HistoryService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(HistoryController.class)
@DisplayName("HistoryController 테스트")
class HistoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HistoryService historyService;

    private static List<History> historyList;

    @BeforeAll
    static void setup() {
        History history1 = new History(
            1L,
            1L,
            2L,
            Action.ADD,
            LocalDateTime.parse("2022-04-05T19:11:11", DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        );
        History history2 = new History(
            1L,
            1L,
            2L,
            Action.MOVE,
            LocalDateTime.parse("2022-04-05T20:11:11", DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        );
        History history3 = new History(
            1L,
            1L,
            2L,
            Action.UPDATE,
            LocalDateTime.parse("2022-04-05T21:11:11", DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        );
        History history4 = new History(
            1L,
            1L,
            2L,
            Action.REMOVE,
            LocalDateTime.parse("2022-04-05T22:11:11", DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        );
        historyList = Arrays.asList(history1, history2, history3, history4);
    }

    @Nested
    @DisplayName("GET /history 요청이 들어왔을때,")
    class GetHistoryTest {

        @Nested
        @DisplayName("기존 활동기록들이 존재하면")
        class HistoryExistTest {

            @Test
            @DisplayName("각 행동의 이니셜, 카드 아이디, 이전 컬럼 아이디, 현재 컬럼아이디, 수정날짜 정보를 JSON 형식으로 반환한다.")
            void historyList() throws Exception {
                DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
                // given
                given(historyService.findHistories())
                    .willReturn(historyList);

                // when
                ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/history"));

                // then
                result.andExpect(status().isOk())
                    .andExpect(jsonPath("$[0]").exists())
                    .andExpect(jsonPath("$[1]").exists())
                    .andExpect(jsonPath("$[2]").exists())
                    .andExpect(jsonPath("$[3]").exists())

                    .andExpect(jsonPath("$[0].action", is(historyList.get(3).getAction().name())))
                    .andExpect(jsonPath("$[0].cardId", is(historyList.get(3).getCardId().intValue())))
                    .andExpect(jsonPath("$[0].oldColumn", is(historyList.get(3).getColumnsId().intValue())))
                    .andExpect(jsonPath("$[0].newColumn", is(historyList.get(3).getColumnsId().intValue())))
                    .andExpect(jsonPath("$[0].modifiedDate", is(historyList.get(3).getModifiedDate().format(dateTimeFormat))))

                    .andExpect(jsonPath("$[1].action", is(historyList.get(2).getAction().name())))
                    .andExpect(jsonPath("$[1].cardId", is(historyList.get(2).getCardId().intValue())))
                    .andExpect(jsonPath("$[1].oldColumn", is(historyList.get(2).getColumnsId().intValue())))
                    .andExpect(jsonPath("$[1].newColumn", is(historyList.get(2).getColumnsId().intValue())))
                    .andExpect(jsonPath("$[1].modifiedDate", is(historyList.get(2).getModifiedDate().format(dateTimeFormat))))

                    .andExpect(jsonPath("$[2].action", is(historyList.get(1).getAction().name())))
                    .andExpect(jsonPath("$[2].cardId", is(historyList.get(1).getCardId().intValue())))
                    .andExpect(jsonPath("$[2].oldColumn", is(historyList.get(0).getColumnsId().intValue())))
                    .andExpect(jsonPath("$[2].newColumn", is(historyList.get(1).getColumnsId().intValue())))
                    .andExpect(jsonPath("$[2].modifiedDate", is(historyList.get(1).getModifiedDate().format(dateTimeFormat))))

                    .andExpect(jsonPath("$[3].action", is(historyList.get(0).getAction().name())))
                    .andExpect(jsonPath("$[3].cardId", is(historyList.get(0).getCardId().intValue())))
                    .andExpect(jsonPath("$[3].oldColumn", is(historyList.get(0).getColumnsId().intValue())))
                    .andExpect(jsonPath("$[3].newColumn", is(historyList.get(0).getColumnsId().intValue())))
                    .andExpect(jsonPath("$[3].modifiedDate", is(historyList.get(0).getModifiedDate().format(dateTimeFormat))));
            }
        }

        @Nested
        @DisplayName("기존 활동기록들이 존재하면")
        class HistoryNotExistTest {

            @Test
            @DisplayName("빈 리스트를 JSON 형식으로 반환한다.")
            void columnListTest() throws Exception {
                // given
                given(historyService.findHistories())
                    .willReturn(Collections.emptyList());

                // when
                ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/history"));

                // then
                result.andExpect(status().isOk())
                    .andExpect(jsonPath("$").isEmpty());
            }
        }
    }
}
