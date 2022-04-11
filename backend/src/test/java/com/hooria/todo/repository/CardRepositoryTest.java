package com.hooria.todo.repository;

import static org.assertj.core.api.Assertions.*;

import com.hooria.todo.domain.Card;
import com.hooria.todo.domain.Device;
import com.hooria.todo.domain.Status;
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
        LocalDateTime datetime = LocalDateTime.of(2022, 4, 10, 0, 0);
        List<Card> cards = List.of(
            new Card(1, Status.TODO, "title1", "content1", "userId1", Device.WEB, datetime, datetime, false, 1),
            new Card(2, Status.TODO, "title2", "content2", "userId1", Device.WEB, datetime, datetime, false, 2)
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
            assertThat(actual.getDevice()).isEqualTo(expected.getDevice());
            assertThat(actual.getCreatedAt()).isEqualTo(expected.getCreatedAt());
            assertThat(actual.getModifiedAt()).isEqualTo(expected.getModifiedAt());
            assertThat(actual.isDeletedYn()).isFalse();
            assertThat(actual.getRowPosition()).isEqualTo(expected.getRowPosition());
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
        Card card = Card.of(Status.TODO, "title4", "content4", "userId1", Device.WEB, 4);

        //when
        long addedCardId = cardRepository.add(card);
        Optional<Card> addedCard = cardRepository.findById(addedCardId);

        //then
        assertThat(addedCard).isNotEmpty()
            .get()
            .hasFieldOrPropertyWithValue("id", 4L)
            .hasFieldOrPropertyWithValue("status", card.getStatus())
            .hasFieldOrPropertyWithValue("title", card.getTitle())
            .hasFieldOrPropertyWithValue("content", card.getContent())
            .hasFieldOrPropertyWithValue("userId", card.getUserId())
            .hasFieldOrPropertyWithValue("device", card.getDevice())
            .hasFieldOrPropertyWithValue("deletedYn", card.isDeletedYn())
            .hasFieldOrPropertyWithValue("rowPosition", card.getRowPosition());
    }

    @Test
    @Transactional
    @DisplayName("DB에 수정사항을 반영하고 변경된 Card를 반환한다.")
    void update() {
        //given
        long id = 1;
        LocalDateTime datetime = LocalDateTime.of(2022, 4, 10, 1, 0);
        Card existingCard = cardRepository.findById(id).get();
        Card card = new Card(existingCard.getId(),
            Status.IN_PROGRESS,
            existingCard.getTitle(),
            existingCard.getContent(),
            existingCard.getUserId(),
            existingCard.getDevice(),
            existingCard.getCreatedAt(),
            datetime,
            existingCard.isDeletedYn(),
            1
        );

        //when
        Card updatedCard = cardRepository.update(card);

        //then
        assertThat(updatedCard.getId()).isEqualTo(existingCard.getId());
        assertThat(updatedCard.getStatus()).isEqualTo(Status.IN_PROGRESS);
        assertThat(updatedCard.getModifiedAt()).isEqualTo(datetime);
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
