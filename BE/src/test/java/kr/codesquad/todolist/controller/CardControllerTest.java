package kr.codesquad.todolist.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import kr.codesquad.todolist.domain.Card;
import kr.codesquad.todolist.dto.card.CardResponse;
import kr.codesquad.todolist.dto.card.CreateCardRequest;
import kr.codesquad.todolist.service.CardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CardController.class)
class CardControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CardService cardService;

    CreateCardRequest createCardRequest;

    @BeforeEach
    void setUp() {
        createCardRequest = new CreateCardRequest("iOS", 1, "subject", "contents");
    }

    @Test
    void create() throws Exception {

        //given
        Card card = Card.of(1L, createCardRequest.getAuthor(), createCardRequest.getSectionId(), createCardRequest.getSubject(),
                createCardRequest.getContents(), 1000L, LocalDateTime.now(), LocalDateTime.now(), false);
        CardResponse cardResponse = CardResponse.from(card);
        given(cardService.create(refEq(createCardRequest))).willReturn(cardResponse);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.setDateFormat(DateFormat.getDateTimeInstance());

        //when
        mockMvc.perform(post("/api/cards")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8)
                .content(objectMapper.writeValueAsString(createCardRequest)))

                //then
                .andExpect(status().isCreated())
                .andExpect(content().string(objectMapper.writeValueAsString(cardResponse)));
    }

    @Test
    void findAll() {
    }

    @Test
    void findOne() {
    }

    @Test
    void delete() {
    }

    @Test
    void updateOne() {
    }

    @Test
    void findCardsBySectionId() {
    }

    @Test
    void moveOne() {
    }
}
