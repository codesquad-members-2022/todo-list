package com.hooria.todo.repository;

import com.hooria.todo.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class MemberRepositoryTest {

    MemberRepository repository;

    @Autowired
    MemberRepositoryTest(DataSource dataSource) {
        this.repository = new MemberRepository(dataSource);
    }

    @Test
    @DisplayName("인자로 주어진 신규 가입자를 저장소에 저장한다.")
    void insertSuccess() {

        // given
        Member member = new Member(0, "userId4", "password4", "name4");

        // when
        Member result = repository.insert(member);

        // then
        assertThat(result).usingRecursiveComparison().isEqualTo(member);
    }

    @Test
    @DisplayName("저장소에 저장되어 있는 전체 사용자 목록을 읽어 반환한다.")
    void findAllSuccess() {

        // given
        List<Member> members = List.of(
                new Member(1, "userId1", "password1", "name1"),
                new Member(2, "userId2", "password2", "name2"),
                new Member(3, "userId3", "password3", "name3")
        );

        // when
        List<Member> result = repository.findAll();

        // then
        assertThat(result.size()).isEqualTo(members.size());

        int size = result.size();
        for (int index = 0; index < size; ++index) {
            Member member1 = result.get(index);
            Member member2 = members.get(index);

            assertThat(member1).usingRecursiveComparison().isEqualTo(member2);
        }
    }

    @Test
    @DisplayName("인자로 주어진 'userId'를 가진 사용자를 저장소에서 찾아 반환한다.")
    void findByIdSuccess() {
        // given
        String userId = "userId1";
        Member member = new Member(1, userId, "password1", "name1");

        // when
        Optional<Member> result = repository.findById("userId1");

        // then
        assertThat(result).isNotEmpty();

        Member resultMember = result.get();
        assertThat(resultMember).usingRecursiveComparison().isEqualTo(member);
    }
}
