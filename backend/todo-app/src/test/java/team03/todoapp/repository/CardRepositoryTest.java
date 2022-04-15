package team03.todoapp.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import javax.sql.DataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import team03.todoapp.controller.CardLocation;
import team03.todoapp.controller.dto.CardAddFormRequest;
import team03.todoapp.repository.domain.Card;

@JdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CardRepositoryTest {

    private CardRepository cardRepository;
    private Card card;

    @Autowired
    public CardRepositoryTest(DataSource dataSource) {
        this.cardRepository = new CardRepository(dataSource);
    }

    @BeforeEach
    void setUp() {
        cardRepository.deleteAll();

        CardAddFormRequest cardAddFormRequest = new CardAddFormRequest("제목입니다", "내용입니다", "sam",
            CardLocation.done);
        card = cardAddFormRequest.toEntity();
    }

    void createCards() {
        CardAddFormRequest cardAddFormRequest1 = new CardAddFormRequest("카드1", "내용1", "sam1",
            CardLocation.done);
        CardAddFormRequest cardAddFormRequest2 = new CardAddFormRequest("카드2", "내용2", "sam2",
            CardLocation.done);
        CardAddFormRequest cardAddFormRequest3 = new CardAddFormRequest("카드3", "내용3", "sam3",
            CardLocation.done);

        cardRepository.insert(cardAddFormRequest1.toEntity());
        cardRepository.insert(cardAddFormRequest2.toEntity());
        cardRepository.insert(cardAddFormRequest3.toEntity());
    }

    @Test
    @DisplayName("저장할 카드정보를 전달하면 카드를 저장하고, 저장된 카드 ID를 반환한다.")
    void insertTest() {
        //when
        Long savedCardId = cardRepository.insert(card);
        Card foundCard = cardRepository.findById(savedCardId).get();

        //then
        assertThat(savedCardId).isEqualTo(foundCard.getId());
        assertThat(card.getTitle()).isEqualTo("제목입니다");
        assertThat(card.getContent()).isEqualTo("내용입니다");
        assertThat(card.getWriter()).isEqualTo("sam");
        assertThat(card.getCurrentLocation()).isEqualTo(CardLocation.done);
    }

    @Test
    @DisplayName("카드 조회 시 저장 된 카드가 없다면 비어있는 리스트를 반환한다.")
    void finalAllErrorTest() {
        assertThat(cardRepository.findAll()).hasSize(0);
    }

    @Test
    @DisplayName("카드 조회 시 저장 된 모든 카드를 반환한다.")
    void finalAllTest() {
        //given
        createCards();

        //when
        List<Card> cards = cardRepository.findAll();

        //then
        assertThat(cards).hasSize(3);
    }

    @Test
    @DisplayName("수정 할 카드정보를 전달하면 카드 정보를 수정한다.")
    void updateTest() {
        //given
        card.update("수정된 제목", "수정된 내용");

        //when
        cardRepository.update(card);

        //then
        assertThat(card.getTitle()).isEqualTo("수정된 제목");
        assertThat(card.getContent()).isEqualTo("수정된 내용");
    }

    @Test
    @DisplayName("저장 된 카드ID를 전달하면, 해당 카드를 삭제한다.")
    void deleteTest() {
        //given
        Long savedCardId = cardRepository.insert(card);

        //when
        cardRepository.delete(savedCardId);

        //then
        assertThat(cardRepository.findById(savedCardId)).isEmpty();
    }

}
