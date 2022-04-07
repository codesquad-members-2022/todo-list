package todolist.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import todolist.domain.Card;
import todolist.dto.CardDto;
import todolist.repository.CardMemoryRepository;
import todolist.repository.TodoRepository;

import static org.assertj.core.api.Assertions.assertThat;

class CardServiceTest {

    private TodoRepository<Card> repository= new CardMemoryRepository();
    private CardService service = new CardService(repository);

    @Test
    @DisplayName("요청으로 들어온 CardDto 객체를 넘기면 Id값을 가진 CardDto를 반환한다.")
    void addCard() {
        CardDto cardDto = new CardDto("해야할 일", "제목", "내용");

        CardDto result = service.addCard(cardDto);

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getSection()).isEqualTo("해야할 일");
        assertThat(result.getTitle()).isEqualTo("제목");
        assertThat(result.getContent()).isEqualTo("내용");
    }

}
