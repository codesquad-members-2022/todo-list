package com.ijava.todolist.card.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ijava.todolist.card.controller.dto.*;
import com.ijava.todolist.card.domain.Card;
import com.ijava.todolist.card.service.CardActionService;
import com.ijava.todolist.card.service.CardService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CardController.class)
class CardControllerTest {

    private static final String CARDS_URL = "/cards";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CardService cardService;

    @MockBean
    private CardActionService cardActionService;

    @Autowired
    private ObjectMapper objectMapper;

    @Nested
    @DisplayName("카드 목록 조회 요청이 들어왔을 때(GET /cards)")
    class GETCardsTest {

        @Nested
        @DisplayName("칼럼 id 가 있으면")
        class ColumnIdExistTest {

            @Test
            void 카드_목록을_반환한다() throws Exception {
                // given
                DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
                LocalDateTime date = LocalDateTime.of(2022, 4, 5, 20, 40, 0);
                List<Card> cards = List.of(
                        createCard(1L, "제목1", "카드 내용2", 1L, date),
                        createCard(2L, "제목2", "카드 내용2", 1L, date)
                );
                given(cardService.findCardList(any())).willReturn(cards);

                // when
                ResultActions result = mvc.perform(
                        get(CARDS_URL)
                                .queryParam("columnId", "1")
                );

                // then
                result.andExpect(status().isOk())
                        .andExpect(jsonPath("$[0]").exists())
                        .andExpect(jsonPath("$[1]").exists())

                        .andExpect(jsonPath("$[0].cardId", is(cards.get(0).getId().intValue())))
                        .andExpect(jsonPath("$[0].title", is(cards.get(0).getTitle())))
                        .andExpect(jsonPath("$[0].content", is(cards.get(0).getContent())))
                        .andExpect(jsonPath("$[0].createdDate", is(cards.get(0).getCreatedDate().format(dateTimeFormat))))

                        .andExpect(jsonPath("$[1].cardId", is(cards.get(1).getId().intValue())))
                        .andExpect(jsonPath("$[1].title", is(cards.get(1).getTitle())))
                        .andExpect(jsonPath("$[1].content", is(cards.get(1).getContent())))
                        .andExpect(jsonPath("$[1].createdDate", is(cards.get(1).getCreatedDate().format(dateTimeFormat))));
            }
        }

        @Nested
        @DisplayName("칼럼 id 가 없으면")
        class ColumnIdNotExistTest {

            @Test
            void BadRequest_상태코드를_반환한다() throws Exception {
                // when
                ResultActions result = mvc.perform(get(CARDS_URL));

                // then
                result.andExpect(status().isBadRequest());
            }
        }
    }

    @Nested
    @DisplayName("카드 입력 요청이 들어왔을 때(POST /cards)")
    class POSTCardsTest {

        @Nested
        @DisplayName("칼럼 id 와 제목이 정상적으로 들어온 경우")
        class SuccessTest {

            @Test
            void 입력된_id_와_함께_카드_정보를_반환한다() throws Exception {
                // given
                String title = "카드 제목";
                String content = "카드 내용입니다.";
                Long columnId = 1L;
                String requestBody = objectMapper.writeValueAsString(new CardCreateRequest(columnId, title, content));
                CardResponse cardResponse = CardResponse.from(createCard(title, content, columnId, LocalDateTime.now()));
                given(cardActionService.add(any())).willReturn(cardResponse);

                // when
                ResultActions result = mvc.perform(
                        post(CARDS_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody)
                );

                // then
                result.andExpect(status().isCreated())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andExpect(jsonPath("$.cardId", is(cardResponse.getCardId().intValue())))
                        .andExpect(jsonPath("$.title", is(cardResponse.getTitle())))
                        .andExpect(jsonPath("$.content", is(cardResponse.getContent())));
            }
        }
    }

    @Nested
    @DisplayName("카드 수정 요청이 들어왔을 때(PUT /cards/:id)")
    class PUTCardsTest {

        @Nested
        @DisplayName("카드 id, 제목이 정상적으로 들어오면")
        class SuccessTest {

            @Test
            void 수정하고_204_상태코드를_반환한다() throws Exception {
                // given
                Long cardId = 1L;
                String title = "카드 제목";
                String content = "카드 내용입니다.";
                Long columnId = 1L;
                String requestBody = objectMapper.writeValueAsString(new CardUpdateRequest(title, content));
                given(cardActionService.update(anyLong(), any())).willReturn(createCard(cardId, title, content, columnId, LocalDateTime.now()));

                // when
                ResultActions result = mvc.perform(
                        put(CARDS_URL + "/" + cardId.intValue())
                                .content(requestBody)
                                .contentType(MediaType.APPLICATION_JSON)
                );

                // then
                result.andExpect(status().isNoContent());
            }
        }
    }

    @Nested
    @DisplayName("카드 이동 요청이 들어왔을 때(PATCH /cards)")
    class CardMoveTest {

        @Nested
        @DisplayName("카드 id 와 이동할 칼럼 id 가 정상적으로 들어오면")
        class SuccessTest {

            @Test
            void 카드의_칼럼을_변경하고_변경_전후_칼럼_id_정보를_반환한다() throws Exception {
                // given
                Long cardId = 1L;
                Long beforeColumnId = 1L;
                Long afterColumnId = 2L;
                String requestBody = objectMapper.writeValueAsString(new CardMoveRequest(cardId, beforeColumnId));
                given(cardActionService.move(any())).willReturn(new CardMovedResponse(cardId, beforeColumnId, afterColumnId));

                // when
                ResultActions result = mvc.perform(
                        patch(CARDS_URL)
                                .content(requestBody)
                                .contentType(MediaType.APPLICATION_JSON)
                );

                // then
                result.andExpect(status().isOk())
                        .andExpect(jsonPath("$.cardId").exists())
                        .andExpect(jsonPath("$.oldColumn").exists())
                        .andExpect(jsonPath("$.newColumn").exists())
                        .andExpect(jsonPath("$.cardId", is(cardId.intValue())))
                        .andExpect(jsonPath("$.oldColumn", is(beforeColumnId.intValue())))
                        .andExpect(jsonPath("$.newColumn", is(afterColumnId.intValue())));
            }
        }
    }

    private Card createCard(String title, String content, Long columnId, LocalDateTime createdDate) {
        return createCard(1L, title, content, columnId, createdDate);
    }

    private Card createCard(Long cardId, String title, String content, Long columnId, LocalDateTime createdDate) {
        return Card.builder()
                .id(cardId)
                .title(title)
                .content(content)
                .columnsId(columnId)
                .createdDate(createdDate)
                .modifiedDate(createdDate)
                .build();
    }
}