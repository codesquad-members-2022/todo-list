package kr.codesquad.todolist.repository;

import kr.codesquad.todolist.domain.Activity;
import kr.codesquad.todolist.domain.Log;
import kr.codesquad.todolist.repository.log.JdbcLogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class JdbcLogRepositoryTest {

    private final JdbcLogRepository jdbcLogRepository;

    @Autowired
    public JdbcLogRepositoryTest(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcLogRepository = new JdbcLogRepository(namedParameterJdbcTemplate);
    }

    private Log log;

    @BeforeEach
    void setUp() {
        log = new Log(null, "vans", "전의일", null, 1, null, LocalDateTime.now(), Activity.ADD);
    }

    @Test
    @DisplayName("log가 정상적으로 저장되면, true를 반환하여야 한다.")
    void save_success_test() {

        //when
        boolean saved = jdbcLogRepository.save(log);
        System.out.println(Activity.valueOf("ADD"));

        //then
        assertThat(saved).isTrue();
    }

    @Test
    @DisplayName("저장된 모든 log 데이터를 찾고 반환하여야 한다.")
    void findAll() {

        jdbcLogRepository.save(log);

        //when
        List<Log> logs = jdbcLogRepository.findAll();

        //then
        assertThat(logs).hasSize(1);

    }
}
