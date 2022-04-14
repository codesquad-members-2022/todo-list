package com.hooria.todo.controller;

import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hooria.todo.domain.Card;
import com.hooria.todo.domain.Device;
import com.hooria.todo.domain.Status;
import com.hooria.todo.dto.AddCardRequest;
import com.hooria.todo.dto.CardResponse;
import com.hooria.todo.dto.UpdateCardLayoutRequest;
import com.hooria.todo.dto.UpdateCardRequest;
import com.hooria.todo.service.CardService;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(CardController.class)
class CardControllerMockTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    CardService cardService;

    ObjectMapper objectMapper = new ObjectMapper()
        .registerModule(new JavaTimeModule())
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    @Test
    @DisplayName("저장소에 저장되어 있는 전체 타스크 목록을 읽어 반환한다.")
    void getCards() throws Exception {
        //given
        String datetime = "2022-04-10T00:00:00";
        List<CardResponse> cards = List.of(
            new CardResponse(1, "TODO", "title1", "content1", "userId1", "WEB", datetime, datetime,
                false, 1),
            new CardResponse(1, "TODO", "title2", "content2", "userId1", "WEB", datetime, datetime,
                false, 1)
        );

        given(cardService.selectAll()).willReturn(cards);

        //when
        ResultActions resultActions = mvc.perform(get("/api/cards"));

        //then
        resultActions.andExpectAll(
            content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
            content().encoding(StandardCharsets.UTF_8),
            content().string(objectMapper.writeValueAsString(cards)),
            status().isOk()
        );

        verify(cardService).selectAll();
    }

    @Test
    @DisplayName("인자로 주어진 신규 task를 저장소에 저장한다.")
    void addCard() throws Exception {
        //given
        LocalDateTime now = LocalDateTime.now();
        AddCardRequest newCard = new AddCardRequest("TODO", "add test title", "add test content", "userId1", "IOS");
        Card expectedCard = new Card(4, Status.TODO, "add test title", "add test content", "userId1",
            Device.IOS, now, now, false, 0);
        CardResponse expected = expectedCard.toCardResponse();
        given(cardService.add(refEq(newCard))).willReturn(expected);

        //when
        ResultActions resultActions = mvc.perform(post("/api/cards")
            .content(objectMapper.writeValueAsString(newCard))
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding(StandardCharsets.UTF_8)
            .accept(MediaType.APPLICATION_JSON)
        );

        //then
        resultActions.andExpectAll(
            content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
            content().encoding(StandardCharsets.UTF_8),
            content().string(objectMapper.writeValueAsString(expected)),
            status().isOk()
        );

        verify(cardService).add(refEq(newCard));
    }

    @Test
    @DisplayName("인자로 주어진 'id'를 가진 task를 저장소에서 찾아 삭제 처리한다(soft-delete).")
    void deleteCard() throws Exception {
        //given
        long id = 1;
        LocalDateTime datetime = LocalDateTime.of(2022, 4, 10, 0, 0);
        Card expectedCard = new Card(1, Status.TODO, "title1", "content1", "userId1", Device.WEB,
            datetime, datetime, true, 0);
        CardResponse expected = expectedCard.toCardResponse();
        given(cardService.delete(id)).willReturn(expected);

        //when
        ResultActions resultActions = mvc.perform(delete("/api/cards/{id}", id));

        //then
        resultActions.andExpectAll(
            content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
            content().encoding(StandardCharsets.UTF_8),
            content().string(objectMapper.writeValueAsString(expected)),
            status().isOk()
        );
    }

    @Test
    @DisplayName("인자로 주어진 'id'를 가진 task의 정보를 수정한다.")
    void updateCard() throws Exception {
        //given
        long id = 1;
        LocalDateTime now = LocalDateTime.now();
        UpdateCardRequest updateCard = new UpdateCardRequest(
            "MOVE",
            id,
            "title1",
            "content1",
            "userId1",
            "IOS",
            "TODO",
            "DONE"
        );
        Card expectedCard = new Card(id, Status.DONE, "title1", "content1", "userId1", Device.IOS, now, now, false, 1);
        CardResponse expected = expectedCard.toCardResponse();
        given(cardService.update(refEq(updateCard))).willReturn(expected);

        //when
        ResultActions resultActions = mvc.perform(patch("/api/cards/{id}", id)
            .content(objectMapper.writeValueAsString(updateCard))
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding(StandardCharsets.UTF_8)
            .accept(MediaType.APPLICATION_JSON)
        );

        //then
        resultActions.andExpectAll(
            content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
            content().encoding(StandardCharsets.UTF_8),
            content().string(objectMapper.writeValueAsString(expected)),
            status().isOk()
        );

        verify(cardService).update(refEq(updateCard));
    }

    @Test
    @DisplayName("인자로 주어진 'id'를 가진 task들의 rowPosition을  일괄 수정한다.")
    void updateCardsLayout() throws Exception {
        //given
        String datetime = "2022-04-10T00:00:00";
        List<UpdateCardLayoutRequest> updateCard = List.of(
            new UpdateCardLayoutRequest(1, 9),
            new UpdateCardLayoutRequest(2, 8)
        );
        List<CardResponse> cards = List.of(
            new CardResponse(1, "TODO", "title1", "content1", "userId1", "WEB", datetime, datetime,
                false, 9),
            new CardResponse(2, "TODO", "title2", "content2", "userId1", "WEB", datetime, datetime,
                false, 8)
        );

        given(cardService.updateCardsLayout(updateCard)).willReturn(cards);

        //when
        ResultActions resultActions = mvc.perform(patch("/api/cards/layout")
            .content(objectMapper.writeValueAsString(updateCard))
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding(StandardCharsets.UTF_8)
            .accept(MediaType.APPLICATION_JSON)
        );

        //then
        resultActions.andExpectAll(
            content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
            content().encoding(StandardCharsets.UTF_8),
            content().string(objectMapper.writeValueAsString(cards)),
            status().isOk()
        );

        verify(cardService).updateCardsLayout(updateCard);
    }
}
