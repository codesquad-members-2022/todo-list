package com.ijava.todolist.column.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.ijava.todolist.column.domain.Column;
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
@DisplayName("ColumnRepository 테스트")
class ColumnRepositoryTest {

    private static final int TEST_COLUMN_COUNT = 3;

    ColumnRepository columnRepository;

    @Autowired
    public ColumnRepositoryTest(DataSource dataSource) {
        this.columnRepository = new JDBCColumnRepository(dataSource);
    }

    @Nested
    @DisplayName("모든 컬럼들을 가져올 때,")
    class findAllTest {

        @Test
        @DisplayName("기존 컬럼들이 없다면 빈 리스트를 반환한다.")
        void emptyColumn() {
            // when
            Optional<List<Column>> result = columnRepository.findAll();

            // then
            assertThat(result).isEmpty();
        }

        @Test
        @Sql("classpath:sql/test/column-dml-h2.sql")
        @DisplayName("기존 컬럼들이 존재한다면 해당 칼럼들의 리스트를 반환한다.")
        void existColumn() {
            // given
            // 3개의 테스트 데이터 추가

            // when
            Optional<List<Column>> result = columnRepository.findAll();

            // then
            assertThat(result).isPresent();
            assertThat(result.get()).hasSize(TEST_COLUMN_COUNT);
        }
    }
}
