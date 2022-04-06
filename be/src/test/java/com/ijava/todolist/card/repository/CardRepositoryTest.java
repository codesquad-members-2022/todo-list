package com.ijava.todolist.card.repository;

import com.ijava.todolist.card.domain.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CardRepositoryTest {

    private static final long COLUMN_ID = 1L;

    @Autowired
    private CardRepository cardRepository;

    @BeforeEach
    void setUp() {
        cardRepository.deleteAll();
    }

    @Nested
    @DisplayName("카드 저장은")
    class CardSaveTest {

        @Nested
        @DisplayName("카드 정보가 들어오면")
        class CardDataExistTest {

            @Test
            void 새로_등록하고_id_값이_추가된_카드를_반환한다() {
                // given
                Card card = createCardWithColumnsId(COLUMN_ID);

                // when
                Card savedCard = cardRepository.save(card);

                // then
                card.setId(savedCard.getId());
                assertThat(savedCard).isNotNull();
                assertThat(savedCard).isEqualTo(card);
            }
        }
    }

    @Nested
    @DisplayName("특정 컬럼에 속한 카드 목록을 조회할 때")
    class FindByColumnIdTest {

        @Nested
        @DisplayName("컬럼 id 가 있으면")
        class ColumnsIdExistTest {

            @Test
            void 카드_목록을_반환한다() {
                // given
                Card savedCard = saveCardWithColumnsId(COLUMN_ID);
                int expectedSize = 1;

                // when
                Optional<List<Card>> findResult = cardRepository.findByColumnId(COLUMN_ID);

                // then
                assertThat(findResult.isPresent()).isTrue();

                List<Card> cards = findResult.orElseThrow();
                assertThat(cards).size().isEqualTo(expectedSize);
                assertThat(cards.get(0)).isEqualTo(savedCard);
            }
        }
    }

    @Nested
    @DisplayName("카드 개수 조회는")
    class GetCountOfCardsOnColumnsTest {

        @Nested
        @DisplayName("컬럼 id 가 있으면")
        class ColumnsIdExistTest {

            @Test
            void 카드_개수를_반환한다() {
                // given
                int expectedCount = 1;
                saveCardWithColumnsId(COLUMN_ID);

                // when
                Optional<Integer> getCountResult = cardRepository.getCountOfCardsOnColumns(COLUMN_ID);

                // then
                assertThat(getCountResult.isPresent()).isTrue();
                assertThat(getCountResult.orElseThrow()).isEqualTo(expectedCount);
            }
        }
    }

    @Nested
    @DisplayName("카드 한개 조회는")
    class FindByIdTest {

        @Nested
        @DisplayName("저장된 카드의 id 로 조회하면")
        class SavedIdTest {

            @Test
            void 조회된_카드를_반환한다() {
                // given
                Card savedCard = saveCardWithColumnsId(COLUMN_ID);

                // when
                Optional<Card> findResult = cardRepository.findById(savedCard.getId());

                // then
                assertThat(findResult.isPresent()).isTrue();
                assertThat(findResult.orElseThrow()).isEqualTo(savedCard);
            }
        }

        @Nested
        @DisplayName("저장되지 않은 카드의 id 로 조회하면")
        class NotSavedIdTest {

            @Test
            void 카드를_반환하지_않는다() {
                // given
                Long notSavedCardId = 1L;

                // when
                Optional<Card> findResult = cardRepository.findById(notSavedCardId);

                // then
                assertThat(findResult.isPresent()).isFalse();
            }
        }
    }

    private Card createCardWithColumnsId(Long columnId) {
        return new Card("제목", "내용", columnId, LocalDateTime.now(), LocalDateTime.now());
    }

    private Card saveCardWithColumnsId(Long columnsId) {
        return cardRepository.save(createCardWithColumnsId(columnsId));
    }
}