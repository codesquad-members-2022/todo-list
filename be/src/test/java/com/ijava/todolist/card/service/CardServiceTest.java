package com.ijava.todolist.card.service;

import com.ijava.todolist.card.controller.dto.CardCreateRequest;
import com.ijava.todolist.card.controller.dto.CardMoveRequest;
import com.ijava.todolist.card.controller.dto.CardUpdateRequest;
import com.ijava.todolist.card.domain.Card;
import com.ijava.todolist.card.exception.CardNotFoundException;
import com.ijava.todolist.card.repository.CardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class CardServiceTest {

    private static final long COLUMN_ID = 1L;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CardService cardService;

    @BeforeEach
    void setup() {
        cardRepository.deleteAll();
    }

    @Nested
    @DisplayName("카드 목록 조회는")
    class FindCardListTest{

        @Nested
        @DisplayName("칼럼 id 가 있으면")
        class ColumnsIdExistTest {

            @Test
            void 카드_목록을_반환한다() {
                // given
                Card savedCard = saveCardWithColumnsId(COLUMN_ID);

                // when
                List<Card> savedResult = cardService.findCardList(COLUMN_ID);

                // then
                assertThat(savedResult).isNotNull();
                assertThat(savedResult).size().isEqualTo(1);
                assertThat(savedResult.get(0)).isEqualTo(savedCard);
            }
        }

        @Nested
        @DisplayName("칼럼 id 가 null 이면 ")
        class ColumnsIdNotExistTest {

            @Test
            void 빈_리스트를_반환한다() {
                // when
                List<Card> savedResult = cardService.findCardList(null);

                // then
                assertThat(savedResult).isNotNull();
                assertThat(savedResult).size().isEqualTo(0);

            }
        }
    }

    @Nested
    @DisplayName("카드 개수를 조회할 떄")
    class GetCountOfCardCountOnColumnTest {

        @Nested
        @DisplayName("칼럼 id 가 주어지면")
        class ColumnIdExistTest {

            @Test
            void 카드_개수를_반환한다() {
                // given
                int expectedCount = 1;
                saveCardWithColumnsId(COLUMN_ID);

                // when
                int getCountResult = cardService.getCountOfCardsOnColumns(COLUMN_ID);

                // then
                assertThat(getCountResult).isEqualTo(expectedCount);
            }
        }

        @Nested
        @DisplayName("칼럼 id 가 null 이면")
        class ColumnIdNotExistTest {
            @Test
            void 개수_0_을_반환한다_() {
                // given
                Long nullColumnId = null;
                int expectedCount = 0;

                // when
                int getCountResult = cardService.getCountOfCardsOnColumns(nullColumnId);

                // then
                assertThat(getCountResult).isEqualTo(expectedCount);
            }
        }
    }

    @Nested
    @DisplayName("카드 한개 조회는")
    class FindCardByIdTest {

        @Nested
        @DisplayName("저장된 카드를 조회하면")
        class SavedCardTest {

            @Test
            void 조회된_카드가_반환된다() {
                // given
                Card savedCard = saveCard();

                // when
                Card findResult = cardService.findCardById(savedCard.getId());

                // then
                assertThat(findResult).isNotNull();
                assertThat(findResult).isEqualTo(savedCard);
            }
        }

        @Nested
        @DisplayName("저장되지 않은 카드를 조회하면")
        class NotSavedCardTest {

            @Test
            void CardNotFoundException_예외가_발생한다() {
                // given
                String notFoundMessage = "카드를 찾을 수 없습니다";
                Long notSavedId = 1L;

                // then
                assertThatThrownBy(() -> cardService.findCardById(notSavedId))
                        .isInstanceOf(CardNotFoundException.class)
                        .hasMessageContaining(notFoundMessage);
            }
        }
    }

    @Nested
    @DisplayName("카드를 입력할 때")
    class SaveNewCardTest {

        @Nested
        @DisplayName("정보가 정상적으로 넘어오면")
        class SuccessTest {

            @Test
            @Sql("classpath:sql/test/user-dml-h2.sql")
            void 카드를_저장하고_저장된_카드를_반환한다() {
                // given
                Long expectedColumnId = 1L;
                String expectedTitle = "카드 제목";
                String expectedContent = "카드 내용입니다.";
                CardCreateRequest request = new CardCreateRequest(expectedColumnId, expectedTitle, expectedContent);

                // when
                Card savedCard = cardService.saveNewCard(request);

                // then
                assertThat(savedCard).isNotNull();
                assertThat(savedCard.getId()).isNotNull();
                assertThat(savedCard.getColumnsId()).isEqualTo(expectedColumnId);
                assertThat(savedCard.getTitle()).isEqualTo(expectedTitle);
                assertThat(savedCard.getContent()).isEqualTo(expectedContent);
            }
        }
    }

    @Nested
    @DisplayName("카드를 수정할 때")
    class CardUpdateTest {

        @Nested
        @DisplayName("존재하는 카드이면")
        class CardExistTest {

            @Test
            void 수정하고_수정된_카드를_반환한다() {
                // given
                Card savedCard = saveCard();
                String updatedTitle = "수정된 제목";
                String updatedContent = "수정된 내용입니다.";
                CardUpdateRequest updateRequest = new CardUpdateRequest(updatedTitle, updatedContent);

                // when
                Card updatedCard = cardService.updateCard(savedCard.getId(), updateRequest);

                // then
                assertThat(updatedCard).isNotNull();
                assertThat(updatedCard.getId()).isEqualTo(savedCard.getId());
                assertThat(updatedCard.getColumnsId()).isEqualTo(savedCard.getColumnsId());
                assertThat(updatedCard.getTitle()).isEqualTo(updatedTitle);
                assertThat(updatedCard.getContent()).isEqualTo(updatedContent);
                assertThat(updatedCard.getCreatedDate()).isEqualTo(savedCard.getCreatedDate());
                assertThat(updatedCard.getModifiedDate()).isAfter(savedCard.getModifiedDate());
            }
        }
    }

    @Nested
    @DisplayName("카드를 이동할 때")
    class CardMoveTest {

        @Nested
        @DisplayName("카드 id, 칼러 id 가 정상적으로 들어오면")
        class SuccessTest {

            @Test
            void 카드의_칼럼을_변경하고_변경_전후_칼럼_id_정보를_반환한다() {
                // given
                Card savedCard = saveCard();
                Long cardId = savedCard.getId();
                Long beforeColumnId = savedCard.getColumnsId();
                Long afterColumnId = beforeColumnId + 1;
                CardMoveRequest cardMoveRequest = new CardMoveRequest(cardId, afterColumnId);

                // when
                Card movedCard = cardService.moveCard(cardMoveRequest);

                // then
                assertThat(movedCard).isNotNull();
                assertThat(movedCard.getId()).isEqualTo(cardId);
                assertThat(movedCard.getColumnsId()).isEqualTo(afterColumnId);
            }
        }
    }

    private Card saveCard() {
        return saveCardWithColumnsId(COLUMN_ID);
    }

    private Card saveCardWithColumnsId(Long columnsId) {
        LocalDateTime now = LocalDateTime.now();
        return cardRepository.save(Card.builder()
                .title("제목")
                .content("내용")
                .columnsId(columnsId)
                .createdDate(now)
                .modifiedDate(now)
                .build());
    }
}