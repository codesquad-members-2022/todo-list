package com.ijava.todolist.column.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import com.ijava.todolist.column.domain.Column;
import com.ijava.todolist.column.repository.ColumnRepository;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("ColumnService 테스트")
class ColumnServiceTest {

    private static final int TEST_COLUMN_COUNT = 2;

    @Mock
    private ColumnRepository columnRepository;

    @InjectMocks
    private ColumnService columnService;

    private static List<Column> columnList;

    @BeforeAll
    static void setup() {
        Column column1 = Column.builder()
            .id(1L)
            .name("해야할 일")
            .createdDate(
                LocalDateTime.parse("2022-04-05T19:11:11", DateTimeFormatter.ISO_LOCAL_DATE_TIME))
            .modifiedDate(
                LocalDateTime.parse("2022-04-05T19:11:11", DateTimeFormatter.ISO_LOCAL_DATE_TIME))
            .build();
        Column column2 = Column.builder()
            .id(2L)
            .name("하고 있는 일")
            .createdDate(
                LocalDateTime.parse("2022-04-05T19:12:11", DateTimeFormatter.ISO_LOCAL_DATE_TIME))
            .modifiedDate(
                LocalDateTime.parse("2022-04-05T19:12:11", DateTimeFormatter.ISO_LOCAL_DATE_TIME))
            .build();
        columnList = Arrays.asList(column1, column2);
    }

    @Nested
    @DisplayName("모든 컬럼들을 조회할 때,")
    class findColumnsTest {

        @Test
        @DisplayName("기존 컬럼들이 없다면 Optional.empty()를 반환한다.")
        void emptyColumn() {
            // given
            given(columnRepository.findAll())
                .willReturn(Optional.empty());

            // when
            List<Column> result = columnService.findColumns();

            // then
            assertThat(result).isEmpty();
        }

        @Test
        @DisplayName("기존 컬럼들이 존재한다면 해당 칼럼들의 리스트를 반환한다.")
        void existColumn() {
            // given
            given(columnRepository.findAll())
                .willReturn(Optional.of(columnList));

            // when
            List<Column> result = columnService.findColumns();

            // then
            assertThat(result).isNotEmpty()
                .hasSize(TEST_COLUMN_COUNT);
        }
    }
}
