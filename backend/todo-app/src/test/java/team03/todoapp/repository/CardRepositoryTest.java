package team03.todoapp.repository;

import static org.junit.jupiter.api.Assertions.*;

import javax.sql.DataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
    void add() {
        cardRepository.add();
    }
}
