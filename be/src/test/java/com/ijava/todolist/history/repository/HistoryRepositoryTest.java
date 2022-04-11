package com.ijava.todolist.history.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

import com.ijava.todolist.history.Action;
import com.ijava.todolist.history.domain.History;
import com.ijava.todolist.history.exception.HistoryNotSavedException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.test.context.jdbc.Sql;

@JdbcTest
@DisplayName("HistoryRepository 테스트")
class HistoryRepositoryTest {

    private static final int TEST_HISTORY_COUNT = 4;

    HistoryRepository historyRepository;

    @Autowired
    public HistoryRepositoryTest(DataSource dataSource) {
        this.historyRepository = new JdbcHistoryRepository(dataSource);
    }


    @Nested
    @DisplayName("모든 활동기록들을 가져올 때,")
    class findAllTest {

        @Test
        @DisplayName("기존 활동기록이 없다면 Optional.empty()를 반환한다.")
        void emptyHistory() {
            // when
            Optional<List<History>> result = historyRepository.findAll();

            // then
            assertThat(result).isEmpty();
        }

        @Test
        @Sql("classpath:sql/test/history-dml-h2.sql")
        @DisplayName("기존 활동기록들이 존재한다면 해당 기록들의 리스트를 반환한다.")
        void existHistory() {
            // given
            // 4개의 테스트 데이터 추가

            // when
            Optional<List<History>> result = historyRepository.findAll();

            // then
            assertThat(result).isPresent();
            assertThat(result.get()).hasSize(TEST_HISTORY_COUNT);
        }
    }

    @Nested
    @DisplayName("활동기록을 저장할 때,")
    class saveTest {

        @Test
        @DisplayName("활동기록이 null 이라면 HistoryNotSaved 예외를 반환한다.")
        void nullHistory() {
            // given
            History nullHistory = null;

            // when
            Throwable thrown = catchThrowable(() -> historyRepository.save(nullHistory));

            // then
            assertThat(thrown).isInstanceOf(HistoryNotSavedException.class)
                .hasMessageContaining("활동기록을 저장하는데에 실패하였습니다.");
        }

        @Test
        @DisplayName("정상적인 활동기록이라면 아무 예외도 반환하지 않는다.")
        void properHistory() {
            // given
            History properHistory = new History(
                1L,
                1L,
                2L,
                Action.ADD,
                LocalDateTime.parse("2022-04-05T19:11:11", DateTimeFormatter.ISO_LOCAL_DATE_TIME)
            );

            // when
            Throwable thrown = catchThrowable(() -> historyRepository.save(properHistory));

            // then
            assertThat(thrown).doesNotThrowAnyException();
        }
    }
}
