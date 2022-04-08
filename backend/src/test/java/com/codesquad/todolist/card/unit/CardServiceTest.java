package com.codesquad.todolist.card.unit;

import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.codesquad.todolist.card.Card;
import com.codesquad.todolist.card.CardCreateRequest;
import com.codesquad.todolist.card.CardRepository;
import com.codesquad.todolist.card.CardService;

@ExtendWith(MockitoExtension.class)
public class CardServiceTest {

    @InjectMocks
    private CardService cardService;

    @Mock
    private CardRepository cardRepository;

    @Test
    @DisplayName("카드를 생성하면 저장소에 저장된다")
    public void cardCreateTest() {
        // given
        CardCreateRequest createRequest = new CardCreateRequest(1, "제목", "bc", "내용");
        Card dummyCard = new Card.Builder(1, "제목", "bc")
            .content("내용")
            .build();

        given(cardRepository.countByColumn(anyInt())).willReturn(0);
        given(cardRepository.create(any(Card.class))).willReturn(dummyCard);

        // when
        Card createdCard = cardService.create(createRequest);

        // then
        then(cardRepository).should(times(1)).create(any(Card.class));
    }
}
