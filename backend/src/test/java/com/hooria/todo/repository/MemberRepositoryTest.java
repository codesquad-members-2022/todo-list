package com.hooria.todo.repository;

import com.hooria.todo.domain.Member;
import org.assertj.core.api.AssertionsForInterfaceTypes;
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
    @DisplayName("저장소에서 전체 사용자 목록을 읽어 반환한다.")
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
        // TODO - usingRecursiveComparison() 사용하지 않고 요소 별로 비교하기
        AssertionsForInterfaceTypes.assertThat(result).usingRecursiveComparison().isEqualTo(members);
    }

    @Test
    @DisplayName("인자로 주어진 'userId' 를 가진 사용자를 저장소에서 찾아 반환한다.")
    void findByIdSuccess() {
        // given
        String userId = "userId1";
        Member member = new Member(1, userId, "password1", "name1");

        // when
        Optional<Member> result = repository.findById("userId1");

        // then
        assertThat(result).isNotEmpty();

        // TODO
        assertThat(result.get().getId()).isEqualTo(member.getId());
        assertThat(result.get().getUserId()).isEqualTo(member.getUserId());
        assertThat(result.get().getName()).isEqualTo(member.getName());
        assertThat(result.get().getPassword()).isEqualTo(member.getPassword());
    }
}
