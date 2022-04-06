package com.ijava.todolist.column.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ijava.todolist.card.service.CardService;
import com.ijava.todolist.column.domain.Column;
import com.ijava.todolist.column.service.ColumnService;
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

@WebMvcTest(ColumnController.class)
@DisplayName("ColumnController 테스트")
class ColumnControllerTest {

    private static final int EXIST_CARD_COUNT = 5;
    private static final int EMPTY_CARD_COUNT = 0;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ColumnService columnService;

    @MockBean
    private CardService cardService;

    private static List<Column> columnList;

    @BeforeAll
    static void setup() {
        Column column1 = Column.builder()
            .id(1L)
            .name("해야할 일")
            .createdDate(
                LocalDateTime.parse("2022-04-05T19:11:11", DateTimeFormatter.ISO_LOCAL_DATE_TIME))
            .modifiedDate(
                LocalDateTime.parse("2022-04-05T19:11:11", DateTimeFormatter.ISO_LOCAL_DATE_TIME))
            .build();
        Column column2 = Column.builder()
            .id(2L)
            .name("하고 있는 일")
            .createdDate(
                LocalDateTime.parse("2022-04-05T19:12:11", DateTimeFormatter.ISO_LOCAL_DATE_TIME))
            .modifiedDate(
                LocalDateTime.parse("2022-04-05T19:12:11", DateTimeFormatter.ISO_LOCAL_DATE_TIME))
            .build();
        columnList = Arrays.asList(column1, column2);
    }

    @Nested
    @DisplayName("GET /columns 요청이 들어왔을때,")
    class GETColumnsTest {

        @Nested
        @DisplayName("기존 컬럼들이 존재하면")
        class ColumnExistTest {

            @Test
            @DisplayName("각 컬럼들의 아이디, 이름, 해당 컬럼에 속하는 카드 개수를 JSON 형식으로 반환한다.")
            void columnListTest() throws Exception {
                // given
                given(cardService.getCountOfCardsOnColumns(any()))
                    .willReturn(EXIST_CARD_COUNT);
                given(columnService.findColumns())
                    .willReturn(columnList);

                // when
                ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/columns"));

                // then
                result.andExpect(status().isOk())
                    .andExpect(jsonPath("$[0]").exists())
                    .andExpect(jsonPath("$[1]").exists())

                    .andExpect(jsonPath("$[0].columnId", is(columnList.get(0).getId().intValue())))
                    .andExpect(jsonPath("$[0].name", is(columnList.get(0).getName())))
                    .andExpect(jsonPath("$[0].cardCount", is(EXIST_CARD_COUNT)))

                    .andExpect(jsonPath("$[1].columnId", is(columnList.get(1).getId().intValue())))
                    .andExpect(jsonPath("$[1].name", is(columnList.get(1).getName())))
                    .andExpect(jsonPath("$[1].cardCount", is(EXIST_CARD_COUNT)));
            }
        }

        @Nested
        @DisplayName("기존 컬럼들이 존재하지 않으면")
        class ColumnEmptyTest {

            @Test
            @DisplayName("빈 리스트를 JSON 형식으로 반환한다.")
            void columnListTest() throws Exception {
                // given
                given(cardService.getCountOfCardsOnColumns(any()))
                    .willReturn(EMPTY_CARD_COUNT);
                given(columnService.findColumns())
                    .willReturn(Collections.emptyList());

                // when
                ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/columns"));

                // then
                result.andExpect(status().isOk())
                    .andExpect(jsonPath("$").isEmpty());
            }
        }
    }
}
