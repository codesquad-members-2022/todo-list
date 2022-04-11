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
import com.hooria.todo.dto.AddCardParam;
import com.hooria.todo.dto.CardResponse;
import com.hooria.todo.repository.CardRepository;
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
    @MockBean
    CardRepository cardRepository;

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
        AddCardParam newCard = new AddCardParam("TODO", "add test title", "add test content", "userId1", "IOS");
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
}
