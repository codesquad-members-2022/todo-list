package todolist.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import todolist.domain.card.Card;

import static org.assertj.core.api.Assertions.assertThat;

class CardMemoryRepositoryTest {

    private TodoRepository<Card> repository = new CardMemoryRepository();

    @Test
    @DisplayName("Card 객체를 저장한다.")
    void save() {
        Card card = new Card("해야할 일", "제목", "내용");

        Card result = repository.save(card);

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getSection()).isEqualTo("해야할 일");
        assertThat(result.getTitle()).isEqualTo("제목");
        assertThat(result.getContent()).isEqualTo("내용");
    }



}
