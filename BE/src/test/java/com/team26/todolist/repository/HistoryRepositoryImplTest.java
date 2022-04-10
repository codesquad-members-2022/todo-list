package com.team26.todolist.repository;

import static org.assertj.core.api.Assertions.*;

import com.team26.todolist.domain.History;
import java.util.List;
import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource("classpath:application-test.properties")
class HistoryRepositoryImplTest {
    DataSource dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
            .addScript("classpath:sql/schema.sql")
            .addScript("classpath:sql/data.sql")
            .build();

    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

    private final HistoryRepositoryImpl historyRepository = new HistoryRepositoryImpl(jdbcTemplate);

    @Test
    void 전체_History_목록_조회() {
        List<History> histories = historyRepository.findAll();
        assertThat(histories.get(0).getUserId()).isEqualTo("test1");
    }

    @Test
    void findById() {
        History findHistory = historyRepository.findById(1L);
        assertThat(findHistory.getUserId()).isEqualTo("test1");
    }

    @Test
    void save() {
        History history = historyRepository.findById(1L);
        historyRepository.save(history);
        History savedHistory =  historyRepository.findById(5L);
        assertThat(savedHistory.getUserId()).isEqualTo("test1");
    }
}
