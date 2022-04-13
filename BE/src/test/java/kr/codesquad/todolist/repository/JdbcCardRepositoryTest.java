package kr.codesquad.todolist.repository;

import kr.codesquad.todolist.domain.Card;
import kr.codesquad.todolist.repository.card.JdbcCardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.time.LocalDateTime;
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
        card = Card.newCard("vans", 1, "제목", "본문");
    }

    @Test
    @DisplayName("새로운 카드 저장하면 아이디와 orderIndex를 가지는 카드 객체를 반환한다.")
    void save_test() {

        //when
        Card saved = jdbcCardRepository.save(card);

        //then
        assertThat(saved.getMemberId()).isEqualTo(card.getMemberId());
        assertThat(saved.getSectionId()).isEqualTo(card.getSectionId());
        assertThat(saved.getSubject()).isEqualTo(card.getSubject());
        assertThat(saved.getContents()).isEqualTo(card.getContents());
        assertThat(saved.isDeleted()).isFalse();
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getOrderIndex()).isNotNull();
    }

    @Test
    @DisplayName("기존 저장되어있는 카드룰 저장하면 subject와 contents, updated_at을 수정한다.")
    void save_update_test() {


        //given
        Card saved = jdbcCardRepository.save(card);
        LocalDateTime now = LocalDateTime.now();
        Card updatableCard = Card.of(saved.getId(), saved.getMemberId(), saved.getSectionId(), "updatedSubject",
                "updatedContents", saved.getOrderIndex(), saved.getCreatedAt(), now, saved.isDeleted());

        //when
        Card updated = jdbcCardRepository.save(updatableCard);

        //then
        assertThat(updated).isEqualTo(saved);
        assertThat(updated.getMemberId()).isEqualTo(saved.getMemberId());
        assertThat(updated.getSectionId()).isEqualTo(saved.getSectionId());
        assertThat(updated.getSubject()).isEqualTo("updatedSubject");
        assertThat(updated.getContents()).isEqualTo("updatedContents");
        assertThat(updated.getUpdatedAt()).isNotEqualTo(saved.getUpdatedAt());
        assertThat(updated.getUpdatedAt()).isEqualTo(now);
        assertThat(updated.isDeleted()).isFalse();
    }

    @Test
    @DisplayName("id로 card 객체를 찾으면 Optional<Card>를 반환한다.")
    void findById_success_test() {
        //given
        Card saved = jdbcCardRepository.save(card);

        //when
        Optional<Card> target = jdbcCardRepository.findById(saved.getId());

        //then
        assertThat(target).isNotEmpty().get().isEqualTo(saved);
    }

    @Test
    @DisplayName("id로 card 객체를 찾았을때, 저장되어있지않은 card라면 Optional.empty를 반환한다.")
    void findById_fail_test() {

        //when
        Optional<Card> target = jdbcCardRepository.findById(999L);

        //then
        assertThat(target).isEmpty();
    }

    @Test
    @DisplayName("findAll()를 호출하면, 전체 List<Card>를 반환한다.")
    void findAll_test() {

        Card saved = jdbcCardRepository.save(card);

        //when
        List<Card> all = jdbcCardRepository.findAll();

        //then
        assertThat(all).contains(saved);
        assertThat(all).hasSize(8);
    }

    @Test
    @DisplayName("id를 받아서 deleted를 true로 실행하고, 변경되었다면 true를 반환한다.")
    void delete_success_test() {

        //when
        boolean isDeleted = jdbcCardRepository.delete(1L);
        List<Card> all = jdbcCardRepository.findAll();

        //then
        assertThat(isDeleted).isTrue();
        assertThat(all).hasSize(6);
    }

    @Test
    @DisplayName("id를 받아서 상태값 변경을 실행하고, 변경되지않았다면 false를 반환한다.")
    void delete_fail_test() {

        //when
        boolean isDeleted = jdbcCardRepository.delete(999L);
        List<Card> all = jdbcCardRepository.findAll();

        //then
        assertThat(isDeleted).isFalse();
        assertThat(all).hasSize(7);
    }

    @Test
    @DisplayName("움직이고자하는 카드와 (맨 위의 위치로 옮길 경우) 움직일 sectionId와 밑에 위치할 card의 id를 가지면 이동할 수 있다.")
    void move_top_test() {

        //when
        Card saved = jdbcCardRepository.save(card);

        // 움직일 위치 (targetSectionId : 2, targetCardId : 6(현재 최상단) ), 움직이고자하는 카드(saved)
        boolean isMoved = jdbcCardRepository.move(2, 6L, saved.getId());
        Card moved = jdbcCardRepository.findById(saved.getId()).orElseThrow();

        //then
        assertThat(isMoved).isTrue();
        assertThat(moved).isEqualTo(saved);
        assertThat(moved.getSectionId()).isEqualTo(2);
        assertThat(moved.isSameOrderIndex(4000L)).isTrue();
    }

    @Test
    @DisplayName("움직이고자하는 카드와 (카드와 카드의 사이에 들어가는 경우) 움직일 sectionId와 밑에 위치할 card의 id를 가지면 이동할 수 있다.")
    void move_middle_test() {

        //when
        Card saved = jdbcCardRepository.save(card);

        // 움직일 위치 (targetSectionId : 2, targetCardId : 5 ), 움직이고자하는 카드(saved)
        boolean isMoved = jdbcCardRepository.move(2, 5L, saved.getId());
        Card moved = jdbcCardRepository.findById(saved.getId()).orElseThrow();

        //then
        assertThat(isMoved).isTrue();
        assertThat(moved).isEqualTo(saved);
        assertThat(moved.getSectionId()).isEqualTo(2);
        assertThat(moved.isSameOrderIndex(2500L)).isTrue();
    }

    @Test
    @DisplayName("움직이고자하는 카드와 (맨아래로 이동하는 경우) 움직일 sectionId와 card의 id가 음수를 가지면 이동할 수 있다.")
    void move_bottom_test() {

        //when
        Card saved = jdbcCardRepository.save(card);

        // 움직일 위치 (targetSectionId : 2, targetCardId : -1 ), 움직이고자하는 카드(saved)
        boolean isMoved = jdbcCardRepository.move(2, -1L, saved.getId());
        Card moved = jdbcCardRepository.findById(saved.getId()).orElseThrow();

        //then
        assertThat(isMoved).isTrue();
        assertThat(moved).isEqualTo(saved);
        assertThat(moved.getSectionId()).isEqualTo(2);
        assertThat(moved.isSameOrderIndex(500L)).isTrue();
    }

    @Test
    @DisplayName("findBySectionId()를 호출하면 해당하는 sectionId로 section에 포함된 카드들을 orderIndex로 정렬된 List<card>로 반환받는다.")
    void findBySectionId_Test() {

        //given
        Card saved = jdbcCardRepository.save(card);

        //when
        List<Card> section2Cards = jdbcCardRepository.findBySectionId(card.getSectionId());

        //then
        assertThat(section2Cards).contains(saved);
        assertThat(section2Cards).hasSize(4);
        assertThat(section2Cards).allMatch(section2Card -> section2Card.getSectionId().equals(1));

    }

    @Test
    @DisplayName("findBySectionId()를 호출했을 때, 해당 section에 포함된 카드가 없으면 비어있는 List<Card>를 반환한다.")
    void findBySectionId_empty_Test() {

        //when
        List<Card> section2Cards = jdbcCardRepository.findBySectionId(4); // test section

        //then
        assertThat(section2Cards).isEmpty();

    }

}
