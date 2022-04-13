package team03.todoapp.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import javax.sql.DataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import team03.todoapp.controller.dto.CardAddFormRequest;
import team03.todoapp.domain.Card;

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
        CardAddFormRequest cardAddFormRequest = new CardAddFormRequest("제목입니다", "내용입니다", "sam",
            "done");
        card = cardAddFormRequest.toEntity();
    }

    @Test
    @DisplayName("카드를 저장하면 저장 된 카드의 ID를 반환한다.")
    void insertTest() {
        //when
        Long savedCardId = cardRepository.insert(card);
        Optional<Card> foundCard = cardRepository.findById(savedCardId);

        //then
        assertThat(savedCardId).isEqualTo(foundCard.get().getId());
        assertThat(card.getTitle()).isEqualTo(foundCard.get().getTitle());
        assertThat(card.getContent()).isEqualTo(foundCard.get().getContent());
        assertThat(card.getWriter()).isEqualTo(foundCard.get().getWriter());
        assertThat(card.getCurrentLocation()).isEqualTo(foundCard.get().getCurrentLocation());
    }


}
