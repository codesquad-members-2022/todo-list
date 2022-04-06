package com.hooria.todo.repository;

import static org.assertj.core.api.Assertions.*;

import com.hooria.todo.domain.Card;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.transaction.annotation.Transactional;

@JdbcTest
class CardRepositoryTest {

    CardRepository cardRepository;

    @Autowired
    public CardRepositoryTest(DataSource dataSource) {
        this.cardRepository = new CardRepository(dataSource);
    }

    @Test
    @DisplayName("전체 카드 목록을 반환한다.")
    void findAll() {
        //given
        LocalDateTime now = LocalDateTime.now();
        List<Card> cards = List.of(
            new Card(1, 1, "title1", "content1", "userId1", 1, now, now, false, 1),
            new Card(2, 1, "title2", "content2", "userId1", 1, now, now, false, 2)
        );

        //when
        List<Card> result = cardRepository.findAll();

        //then
        assertThat(result).hasSize(cards.size());

        int size = result.size();
        for (int i = 0; i < size; i++) {
            Card actual = result.get(i);
            Card expected = cards.get(i);
            assertThat(actual.getId()).isEqualTo(expected.getId());
            assertThat(actual.getStatus()).isEqualTo(expected.getStatus());
            assertThat(actual.getTitle()).isEqualTo(expected.getTitle());
            assertThat(actual.getContent()).isEqualTo(expected.getContent());
            assertThat(actual.getUserId()).isEqualTo(expected.getUserId());
            assertThat(actual.getApplianceInfo()).isEqualTo(expected.getApplianceInfo());
            assertThat(actual.isDeletedYn()).isFalse();
            assertThat(actual.getIndex()).isEqualTo(expected.getIndex());
        }
    }

    @Test
    @DisplayName("상태별 카드 갯수를 반환한다.")
    void countByStatus() {
        //given

        //when
        int result = cardRepository.countByStatus(1);

        //then
        assertThat(result).isEqualTo(2);
    }

    @Test
    @Transactional
    @DisplayName("카드를 추가하고 추가된 카드의 id를 반환한다.")
    void add() {
        //given
        Card card = Card.of(1, "title4", "content4", "userId1", 1, 4);

        //when
        long addedCardId = cardRepository.add(card);
        Optional<Card> addedCard = cardRepository.findById(addedCardId);

        //then
        assertThat(addedCard).isNotEmpty()
            .get()
            .hasFieldOrPropertyWithValue("id", 4L)
            .hasFieldOrPropertyWithValue("status", 1)
            .hasFieldOrPropertyWithValue("title", "title4")
            .hasFieldOrPropertyWithValue("content", "content4")
            .hasFieldOrPropertyWithValue("userId", "userId1")
            .hasFieldOrPropertyWithValue("applianceInfo", 1)
            .hasFieldOrPropertyWithValue("deletedYn", false)
            .hasFieldOrPropertyWithValue("index", 4);
    }

    @Test
    @Transactional
    @DisplayName("DB에 수정사항을 반영하고 변경된 Card를 반환한다.")
    void update() {
        //given
        Card existingCard = cardRepository.findById(1).get();
        Card card = new Card(existingCard.getId(),
            2,
            existingCard.getTitle(),
            existingCard.getContent(),
            existingCard.getUserId(),
            existingCard.getApplianceInfo(),
            existingCard.getCreatedAt(),
            LocalDateTime.now(),
            existingCard.isDeletedYn(),
            1
        );

        //when
        Card updatedCard = cardRepository.update(card);

        //then
        assertThat(updatedCard.getId()).isEqualTo(existingCard.getId());
        assertThat(updatedCard.getStatus()).isEqualTo(2);
        assertThat(updatedCard.getModifiedAt()).isNotEqualTo(existingCard.getModifiedAt());
    }

    @Test
    @Transactional
    @DisplayName("카드를 삭제하고 변경된 row 수를 반환한다.")
    void delete() {
        //given

        //when
        long deletedId = cardRepository.delete(1);
        Optional<Card> deletedCard = cardRepository.findById(deletedId);

        //then
        assertThat(deletedCard).isNotEmpty()
            .get()
            .hasFieldOrPropertyWithValue("deletedYn", true);
    }
}
