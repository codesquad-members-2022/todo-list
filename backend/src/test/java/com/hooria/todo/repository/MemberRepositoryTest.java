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
        assertThat(result.getId()).isEqualTo(member.getId());
        assertThat(result.getUserId()).isEqualTo(member.getUserId());
        assertThat(result.getPassword()).isEqualTo(member.getPassword());
        assertThat(result.getName()).isEqualTo(member.getName());

        /*
            assertThat(result).usingRecursiveComparison().isEqualTo(member);
            객체 비교 시 usingRecursiveComparison() 메서드를 사용하는 검증도 괜찮은 지 궁금합니다! - @Riako
        */
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

            assertThat(member1.getId()).isEqualTo(member2.getId());
            assertThat(member1.getUserId()).isEqualTo(member2.getUserId());
            assertThat(member1.getPassword()).isEqualTo(member2.getPassword());
            assertThat(member1.getName()).isEqualTo(member2.getName());
        }

        /*
            assertThat(result).usingRecursiveComparison().isEqualTo(members);
            Collection 의 경우에도 usingRecursiveComparison() 메서드를 사용하는 검증도 괜찮은 지 궁금합니다! - @Riako
        */
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

        assertThat(resultMember.getId()).isEqualTo(member.getId());
        assertThat(resultMember.getUserId()).isEqualTo(member.getUserId());
        assertThat(resultMember.getName()).isEqualTo(member.getName());
        assertThat(resultMember.getPassword()).isEqualTo(member.getPassword());
    }
}
