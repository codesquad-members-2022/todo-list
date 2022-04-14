package com.ijava.todolist.history.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

import com.ijava.todolist.history.Action;
import com.ijava.todolist.history.domain.History;
import com.ijava.todolist.history.exception.HistoryNotSavedException;
import com.ijava.todolist.history.repository.HistoryRepository;
import com.ijava.todolist.user.service.UserService;
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
@DisplayName("HistoryService 테스트")
class HistoryServiceTest {

    private static final int TEST_HISTORY_COUNT = 2;
    private static final Long DEFAULT_USER_ID = 1L;

    @Mock
    private HistoryRepository historyRepository;
    @Mock
    private UserService userService;

    @InjectMocks
    private HistoryService historyService;

    private static List<History> historyList;

    @BeforeAll
    static void setup() {
        History history1 = new History(
            1L,
            1L,
            2L,
            2L,
            Action.ADD,
            LocalDateTime.parse("2022-04-05T19:11:11", DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        );
        History history2 = new History(
            1L,
            1L,
            2L,
            3L,
            Action.MOVE,
            LocalDateTime.parse("2022-04-05T20:11:11", DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        );
        historyList = Arrays.asList(history1, history2);
    }

    @Nested
    @DisplayName("모든 활동기록들을 조회할 때,")
    class findHistoriesTest {

        @Test
        @DisplayName("기존 기록이 없다면 빈 리스트를 반환한다.")
        void emptyHistory() {
            // given
            given(historyRepository.findAll())
                .willReturn(Optional.empty());

            // when
            List<History> result = historyService.findHistories();

            // then
            assertThat(result).isEmpty();
        }

        @Test
        @DisplayName("기존 기록들이 존재한다면 해당 기록들의 리스트를 반환한다.")
        void existHistory() {
            // given
            given(historyRepository.findAll())
                .willReturn(Optional.of(historyList));

            // when
            List<History> result = historyService.findHistories();

            // then
            assertThat(result).isNotEmpty()
                .hasSize(TEST_HISTORY_COUNT);
        }
    }

    @Nested
    @DisplayName("활동기록을 저장할 때,")
    class saveHistoryTest {

        @Test
        @DisplayName("null 값인 인자가 있다면 HistoryNotSaved 예외를 반환한다.")
        void nullArgument() {
            // when
            Throwable thrown = catchThrowable(() -> historyService.store(
                1L,
                3L,
                3L,
                null)
            );

            // then
            assertThat(thrown).isInstanceOf(HistoryNotSavedException.class)
                .hasMessageContaining("활동기록을 저장하는데에 실패하였습니다.");
        }

        @Test
        @DisplayName("인자를 정상적으로 넘겨준다면 아무 예외도 발생하지 않는다.")
        void properArgument() {
            // given
            doNothing()
                .when(historyRepository)
                .save(any());
            given(userService.findDefaultUserId())
                .willReturn(DEFAULT_USER_ID);

            // when
            Throwable thrown = catchThrowable(() -> historyService.store(
                1L,
                3L,
                3L,
                Action.ADD)
            );

            // then
            assertThat(thrown).doesNotThrowAnyException();
        }
    }

}
