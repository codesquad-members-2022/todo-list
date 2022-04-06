package com.ijava.todolist.card.service;

import com.ijava.todolist.card.domain.Card;
import com.ijava.todolist.card.exception.CardNotFoundException;
import com.ijava.todolist.card.repository.CardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    private Card saveCard() {
        return saveCardWithColumnsId(COLUMN_ID);
    }

    private Card saveCardWithColumnsId(Long columnsId) {
        LocalDateTime now = LocalDateTime.now();
        return cardRepository.save(new Card("제목", "내용", columnsId, now, now));
    }
}