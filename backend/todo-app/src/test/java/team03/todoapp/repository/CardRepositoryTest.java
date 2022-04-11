package team03.todoapp.repository;

import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import team03.todoapp.controller.dto.CardAddFormRequest;
import team03.todoapp.domain.Card;

@SpringBootTest
class CardRepositoryTest {

    @Autowired
    DataSource datasource;
    @Autowired
    CardRepository cardRepository;

    @Test
//    @Transactional
    void insertTest() {
        CardAddFormRequest cardAddFormRequest = new CardAddFormRequest("제목입니다", "내용입니다", "sam",
            "done");
        Card card = cardAddFormRequest.toCardEntity();
        cardRepository.insert(card);
    }
}
