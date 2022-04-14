package com.ijava.todolist.user.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.ijava.todolist.user.domain.User;
import java.util.Optional;
import javax.sql.DataSource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.test.context.jdbc.Sql;

@JdbcTest
@DisplayName("UserRepository 테스트")
class UserRepositoryTest {

    private static final Long DEFAULT_USER_ID = 1L;

    UserRepository userRepository;

    @Autowired
    public UserRepositoryTest(DataSource dataSource) {
        this.userRepository = new JdbcUserRepository(dataSource);
    }

    @Nested
    @DisplayName("유저 정보를 가져올때,")
    class findByIdTest {

        @Test
        @DisplayName("해당 아이디의 유저가 없다면 빈 객체를 반환한다.")
        void notExistUser() {
            // when
            Optional<User> result = userRepository.findById(DEFAULT_USER_ID);

            // then
            assertThat(result).isEmpty();
        }

        @Test
        @Sql("classpath:sql/test/user-dml-h2.sql")
        @DisplayName("해당 아이디의 유저가 존재한다면 User 객체를 반환한다.")
        void ExistUser() {
            // given
            // 1개의 테스트 데이터 추가

            // when
            Optional<User> result = userRepository.findById(DEFAULT_USER_ID);

            // then
            assertThat(result).isPresent();
            assertThat(result.get()).isInstanceOf(User.class);
            assertThat(result.get().getId()).isEqualTo(DEFAULT_USER_ID);
        }
    }
}
