package com.ijava.todolist.history.util;

import static org.assertj.core.api.Assertions.assertThat;

import com.ijava.todolist.history.Action;
import com.ijava.todolist.history.controller.dto.HistoryResponse;
import com.ijava.todolist.history.domain.History;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class HistoryResolverTest {

    @Nested
    @DisplayName("카드 1을 칼럼1에 ADD 하고")
    class addFirst {

        @Nested
        @DisplayName("칼럼3으로 MOVE 하면")
        class moveSecond {

            @Test
            @DisplayName("해당 기록들을 최신순으로 확인 할 수 있다.")
            void properHistory() {
                // given
                History firstHistory = getHistory(1L, 1L, 1L, Action.ADD, 1);
                History secondHistory = getHistory(1L, 1L, 3L, Action.MOVE, 2);

                // when
                List<HistoryResponse> result = HistoryResolver.resolveHistoryList(
                    Arrays.asList(firstHistory, secondHistory));

                // then
                assertThat(result.get(0).getCardId()).isEqualTo(1L);
                assertThat(result.get(0).getOldColumn()).isEqualTo(1L);
                assertThat(result.get(0).getNewColumn()).isEqualTo(3L);
                assertThat(result.get(0).getAction()).isEqualTo(Action.MOVE.name());

                assertThat(result.get(1).getCardId()).isEqualTo(1L);
                assertThat(result.get(1).getOldColumn()).isEqualTo(1L);
                assertThat(result.get(1).getNewColumn()).isEqualTo(1L);
                assertThat(result.get(1).getAction()).isEqualTo(Action.ADD.name());
            }
        }
    }

    private static History getHistory(Long userId, Long cardId, Long ColumnId, Action action,
        int priority) {
        return new History(
            userId,
            cardId,
            ColumnId,
            action,
            LocalDateTime.parse("2022-04-05T20:11:" + String.format("%02d", priority),
                DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        );
    }
}
