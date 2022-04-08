package team03.todoapp.repository;

import static org.junit.jupiter.api.Assertions.*;

import javax.sql.DataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import team03.todoapp.controller.dto.CardAddFormRequest;
import team03.todoapp.domain.Card;

@SpringBootTest
class CardRepositoryTest {

    @Autowired
    DataSource datasource;
    CardRepository cardRepository;

    @BeforeEach
    void beforeEach() {
        this.cardRepository = new CardRepository(datasource);
    }

    @Test
    @Transactional
    void insertTest() {
        CardAddFormRequest cardAddFormRequest = new CardAddFormRequest("제목입니다","내용입니다","sam","done");
        Card card = cardAddFormRequest.toCardEntity();
        cardRepository.insert(card);
    }
}
