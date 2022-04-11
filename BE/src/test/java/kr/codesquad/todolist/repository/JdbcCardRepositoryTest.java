package kr.codesquad.todolist.repository;

import kr.codesquad.todolist.domain.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;


@JdbcTest
class JdbcCardRepositoryTest {

    private JdbcCardRepository jdbcCardRepository;

    @Autowired
    public JdbcCardRepositoryTest(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcCardRepository = new JdbcCardRepository(namedParameterJdbcTemplate);
    }

    private Card card;

    @BeforeEach
    void setUp() {
        card = Card.of("vans", 1, "제목", "본문");
    }

    @Test
    @DisplayName("새로운 카드 저장하면 아이디값을 가지는 카드 객체를 반환한다.")
    void save_test() {

        //when
        Card saved = jdbcCardRepository.save(card);

        //then
        assertThat(saved.getUserId()).isEqualTo(card.getUserId());
        assertThat(saved.getColumnId()).isEqualTo(card.getColumnId());
        assertThat(saved.getSubject()).isEqualTo(card.getSubject());
        assertThat(saved.getContents()).isEqualTo(card.getContents());
        assertThat(saved.isDeleted()).isFalse();
    }

    @Test
    @DisplayName("id로 card 객체를 찾으면 Optional<Card>를 반환한다.")
    void findById_success_test() {
        //given
        Card card = jdbcCardRepository.save(this.card);

        //when
        Optional<Card> target = jdbcCardRepository.findById(card.getId());

        //then
        assertThat(target).isNotEmpty();
        assertThat(target.get()).isEqualTo(card);
    }

    @Test
    @DisplayName("id로 card 객체를 찾았을때, 저장되어있지않은 card라면 Optional.empty를 반환한다.")
    void findById_fail_test() {

        //when
        Optional<Card> target = jdbcCardRepository.findById(4L);

        //then
        assertThat(target).isEmpty();
    }

    @Test
    @DisplayName("findAll()를 호출하면, List<Card>를 반환한다.")
    void findAll_test() {

        Card saved = jdbcCardRepository.save(card);

        //when
        List<Card> all = jdbcCardRepository.findAll();

        //then
        assertThat(all).contains(saved);
        assertThat(all).hasSize(3);
    }

    @Test
    @DisplayName("id를 받아서 상태값 변경을 실행하고, 변경되었다면 true를 변경되지않았다면 false를 반환한다.")
    void delete_success_test() {

        //when
        boolean isDeleted = jdbcCardRepository.delete(1L);
        List<Card> all = jdbcCardRepository.findAll();

        //then
        assertThat(isDeleted).isTrue();
        assertThat(all).hasSize(1);
    }

    @Test
    @DisplayName("id를 받아서 상태값 변경을 실행하고, 변경되었다면 true를 변경되지않았다면 false를 반환한다.")
    void delete_fail_test() {

        //when
        boolean isDeleted = jdbcCardRepository.delete(3L);
        List<Card> all = jdbcCardRepository.findAll();

        //then
        assertThat(isDeleted).isFalse();
        assertThat(all).hasSize(2);
    }

}
