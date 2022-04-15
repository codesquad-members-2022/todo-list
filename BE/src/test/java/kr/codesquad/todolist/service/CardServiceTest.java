package kr.codesquad.todolist.service;

import kr.codesquad.todolist.domain.Card;
import kr.codesquad.todolist.dto.card.CardResponse;
import kr.codesquad.todolist.dto.card.CreateCardRequest;
import kr.codesquad.todolist.repository.card.CardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CardServiceTest {

    @InjectMocks
    CardService cardService;
    @Mock
    CardRepository cardRepository;

    CreateCardRequest createCardRequest;

    @BeforeEach
    void setUp() {
        createCardRequest = new CreateCardRequest("iOS", 1, "제목", "본문");
    }

    @Test
    @DisplayName("CardRequest를 받으면 새로운 카드를 생성하고 CardResonse를 반환한다.")
    void create_test() {
        //given
        Card card = Card.of(1L, createCardRequest.getAuthor(), createCardRequest.getSectionId(), createCardRequest.getSubject(),
                createCardRequest.getContents(), 1000L, LocalDateTime.now(), LocalDateTime.now(), false);
        given(cardRepository.save(any())).willReturn(card);

        //when
        CardResponse cardResponse = cardService.create(createCardRequest);

        //then
        assertThat(cardResponse.getAuthor()).isEqualTo(card.getMemberId());
        assertThat(cardResponse.getSectionId()).isEqualTo(card.getSectionId());
        assertThat(cardResponse.getSubject()).isEqualTo(card.getSubject());
        assertThat(cardResponse.getContents()).isEqualTo(card.getContents());
        assertThat(cardResponse.getId()).isEqualTo(1L);

    }

    @Test
    @DisplayName("")
    void update_test() {
        //given

    }

    @Test
    void move() {
    }

    @Test
    void findCardsOfSection() {
    }

    @Test
    void findOne() {
    }

    @Test
    void findAllbySections() {
    }

    @Test
    void delete() {
    }

    @Test
    void findSection() {
    }
}
