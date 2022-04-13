package com.ijava.todolist.history.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ijava.todolist.history.Action;
import com.ijava.todolist.history.domain.History;
import com.ijava.todolist.history.repository.dto.JoinedHistory;
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

    private static List<JoinedHistory> historyList;

    @BeforeAll
    static void setup() {

        Long userId = 1L;
        String userName = "익명사용자이름";
        Long cardId1 = 1L;
        String cardTitle1 = "장보기";
        Long columnId1 = 1L;
        Long columnId2 = 2L;
        String columnName1 = "해야할일";
        String columnName2 = "해야할일";

        LocalDateTime date1 = LocalDateTime.parse("2022-04-05T19:11:11", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        LocalDateTime date2 = LocalDateTime.parse("2022-04-05T20:11:11", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        LocalDateTime date3 = LocalDateTime.parse("2022-04-05T21:11:11", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        LocalDateTime date4 = LocalDateTime.parse("2022-04-05T21:11:11", DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        JoinedHistory history1 = JoinedHistory.builder()
                .id(1L)
                .cardId(cardId1)
                .cardTitle(cardTitle1)
                .userId(userId)
                .userName(userName)
                .oldColumnsId(columnId1)
                .oldColumnName(columnName1)
                .newColumnsId(columnId1)
                .newColumnName(columnName1)
                .action(Action.ADD)
                .createdDate(date1)
                .modifiedDate(date1)
                .build();
        JoinedHistory history2 = JoinedHistory.builder()
                .id(2L)
                .cardId(cardId1)
                .cardTitle(cardTitle1)
                .userId(userId)
                .userName(userName)
                .oldColumnsId(columnId1)
                .oldColumnName(columnName1)
                .newColumnsId(columnId2)
                .newColumnName(columnName2)
                .action(Action.MOVE)
                .createdDate(date2)
                .modifiedDate(date2)
                .build();
        JoinedHistory history3 = JoinedHistory.builder()
                .id(3L)
                .cardId(cardId1)
                .cardTitle(cardTitle1)
                .userId(userId)
                .userName(userName)
                .oldColumnsId(columnId2)
                .oldColumnName(columnName2)
                .newColumnsId(columnId2)
                .newColumnName(columnName2)
                .action(Action.UPDATE)
                .createdDate(date3)
                .modifiedDate(date3)
                .build();
        JoinedHistory history4 = JoinedHistory.builder()
                .id(4L)
                .cardId(cardId1)
                .cardTitle(cardTitle1)
                .userId(userId)
                .userName(userName)
                .oldColumnsId(columnId2)
                .oldColumnName(columnName2)
                .newColumnsId(columnId2)
                .newColumnName(columnName2)
                .action(Action.REMOVE)
                .createdDate(date4)
                .modifiedDate(date4)
                .build();


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
                given(historyService.findAllJoinedHistory())
                    .willReturn(historyList);

                // when
                ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/history"));

                // then
                result.andExpect(status().isOk())
                    .andExpect(jsonPath("$[0]").exists())
                    .andExpect(jsonPath("$[1]").exists())
                    .andExpect(jsonPath("$[2]").exists())
                    .andExpect(jsonPath("$[3]").exists());
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
