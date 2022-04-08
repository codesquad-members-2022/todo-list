package com.codesquad.todolist.card.unit;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.codesquad.todolist.card.Card;
import com.codesquad.todolist.card.CardRepository;
import com.codesquad.todolist.card.CardService;
import com.codesquad.todolist.card.dto.CardUpdateRequest;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("CardService 단위 테스트")
public class CardServiceTest {

    @InjectMocks
    private CardService cardService;

    @Mock
    private CardRepository cardRepository;

    @Test
    @DisplayName("변경된 데이터로 카드 정보가 업데이트된다")
    public void cardUpdateTest() {
        // given
        CardUpdateRequest request = new CardUpdateRequest("제목", "내용", "작성자");
        Card card = new Card(1, 1, "제목", "내용", "작성자", 1, LocalDateTime.now());
        given(cardRepository.findById(1)).willReturn(card);

        // when
        cardService.updateCard(1, request);

        // then
        then(cardRepository).should(times(1)).update(card);
    }

    public void cardCreateTest() {
        // given
        CardCreateRequest createRequest = new CardCreateRequest(1, "제목", "작성자", "내용");
        Card card = new Card(1, "제목", "내용", "작성자", 1);

        given(cardRepository.countByColumn(anyInt())).willReturn(0);
        given(cardRepository.create(any(Card.class))).willReturn(card);

        // when
        Card createdCard = cardService.create(createRequest);

        // then
        then(cardRepository).should(times(1)).create(any(Card.class));
    }
}
