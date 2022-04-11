package com.codesquad.todolist.card.unit;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.codesquad.todolist.card.Card;
import com.codesquad.todolist.card.CardRepository;
import com.codesquad.todolist.card.CardService;
import com.codesquad.todolist.card.dto.CardCreateRequest;
import com.codesquad.todolist.card.dto.CardUpdateRequest;

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
        CardUpdateRequest request = new CardUpdateRequest("변경된 제목", "변경된 내용", "작성자");
        Card card = new Card(1, 1, "제목", "내용", "작성자", 1, LocalDateTime.now());
        given(cardRepository.findById(1)).willReturn(Optional.of(card));

        // when
        cardService.update(1, request);

        // then
        then(cardRepository).should(times(1)).update(card);
    }

    @Test
    @DisplayName("카드 생성시 저장소에 카드 데이터가 저장된다")
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

    @Test
    @DisplayName("카드 삭제 요청 시 카드가 삭제된다") //
    public void cardDeleteTest() {
        // given
        Card card = new Card(1, 1, "제목", "내용", "작성자", 1, LocalDateTime.now());
        given(cardRepository.findById(anyInt())).willReturn(Optional.ofNullable(card));

        // when
        cardService.delete(card.getCardId());

        // then
        then(cardRepository).should(times(1)).deleteById(1);

    }
}
