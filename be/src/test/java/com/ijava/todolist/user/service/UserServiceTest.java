package com.ijava.todolist.user.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.BDDMockito.given;

import com.ijava.todolist.user.domain.User;
import com.ijava.todolist.user.exception.UserNotFoundException;
import com.ijava.todolist.user.repository.UserRepository;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("UserService 테스트")
class UserServiceTest {

    private static final Long DEFAULT_USER_ID = 1L;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Nested
    @DisplayName("기본 회원 아이디를 조회할때,")
    class findDefaultUserIdTest {

        @Test
        @DisplayName("기본 회원이 존재하면 예외가 발생하지 않고, 아이디를 반환한다.")
        void defaultUserExist() {
            // given
            User defaultUser = new User(
                1L,
                "defaultUser",
                LocalDateTime.parse("2022-04-05T19:12:11", DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                LocalDateTime.parse("2022-04-05T19:12:11", DateTimeFormatter.ISO_LOCAL_DATE_TIME)
            );
            given(userRepository.findById(DEFAULT_USER_ID))
                .willReturn(Optional.of(defaultUser));

            // when
            Long resultId = userService.findDefaultUserId();
            Throwable thrown = catchThrowable(() -> userService.findDefaultUserId());

            // then
            assertThat(resultId).isEqualTo(DEFAULT_USER_ID);
            assertThat(thrown).doesNotThrowAnyException();
        }

        @Test
        @DisplayName("기본 회원이 존재하지 않으면 UserNotFoundException 예외가 발생한다.")
        void defaultUserNotExist() {
            // given
            given(userRepository.findById(DEFAULT_USER_ID))
                .willReturn(Optional.empty());

            // when
            Throwable thrown = catchThrowable(() -> userService.findDefaultUserId());

            // then
            assertThat(thrown).isInstanceOf(UserNotFoundException.class)
                .hasMessageContaining("사용자를 찾을 수 없습니다.");
        }
    }
}
