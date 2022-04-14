package codesquad.todo.domain.work;

import codesquad.todo.domain.user.User;
import codesquad.todo.domain.user.UserRepository;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
@SpringBootTest
class JdbcTemplateWorkRepositoryTest {

    @Autowired
    JdbcTemplateWorkRepository workRepository;
    @Autowired
    UserRepository userRepository;

    User testMan;

    @BeforeAll
    void init() {
        testMan = new User("testMan");
        userRepository.save(testMan);
    }

    @Test
    @DisplayName("Work를 저장하고 조회시 같은 내용의 Work가 반환되어야 한다.")
    public void savaTest() {
        Work work = Work.builder()
                .title("test")
                .content("content")
                .author(testMan)
                .workStatus(WorkStatus.TODO)
                .statusIndex(0)
                .createDateTime(LocalDateTime.now())
                .lastModifiedDateTime(LocalDateTime.now())
                .build();
        workRepository.save(work);
        Work findWork = workRepository.findById(work.getId()).get();
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(findWork.getId()).as("work id는 1L").isEqualTo(1L);
        softAssertions.assertThat(findWork.getWorkStatus()).isEqualTo(WorkStatus.TODO);
        softAssertions.assertThat(findWork.getAuthor().getId()).isEqualTo(testMan.getId()); //1, testMan(2)
        softAssertions.assertThat(findWork.getTitle()).isEqualTo("test");
        softAssertions.assertAll();
    }

    @Test
    @DisplayName("Id와 Status로 조회시 해당 Work의 list를 반환해야한다.")
    public void findByUserIdAndStatusTest() {
        Work work1 = Work.builder()
                .title("test1")
                .content("content1")
                .author(testMan)
                .workStatus(WorkStatus.TODO)
                .statusIndex(0)
                .createDateTime(LocalDateTime.now())
                .lastModifiedDateTime(LocalDateTime.now())
                .build();

        Work work2 = Work.builder()
                .title("test2")
                .content("content2")
                .author(testMan)
                .workStatus(WorkStatus.TODO)
                .statusIndex(1)
                .createDateTime(LocalDateTime.now())
                .lastModifiedDateTime(LocalDateTime.now())
                .build();

        workRepository.save(work1);
        workRepository.save(work2);

        List<Work> workList = workRepository.findByUserIdAndStatus(WorkStatus.TODO, testMan.getId());
        assertThat(workList.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("해당 Status의 총 Work의 수를 반환해야 한다.")
    public void countOfStatusTest() {
        Work work1 = Work.builder()
                .title("test1")
                .content("content1")
                .author(testMan)
                .workStatus(WorkStatus.TODO)
                .statusIndex(0)
                .createDateTime(LocalDateTime.now())
                .lastModifiedDateTime(LocalDateTime.now())
                .build();

        Work work2 = Work.builder()
                .title("test2")
                .content("content2")
                .author(testMan)
                .workStatus(WorkStatus.TODO)
                .statusIndex(1)
                .createDateTime(LocalDateTime.now())
                .lastModifiedDateTime(LocalDateTime.now())
                .build();

        workRepository.save(work1);
        workRepository.save(work2);

        assertThat(workRepository.countOfStatus(testMan.getId(), WorkStatus.TODO)).isEqualTo(2);
    }

    @Test
    @DisplayName("사용자 id, status, statusIndex를 통해 하나의 Work를 조회 한다.(성공)")
    public void findOneSuccessTest() {
        Work work1 = Work.builder()
                .title("test1")
                .content("content1")
                .author(testMan)
                .workStatus(WorkStatus.TODO)
                .statusIndex(0)
                .createDateTime(LocalDateTime.now())
                .lastModifiedDateTime(LocalDateTime.now())
                .build();
        workRepository.save(work1);

        Work findWork = workRepository.findOne(testMan.getId(), WorkStatus.TODO, 0).get();
        assertThat(findWork.getTitle()).isEqualTo("test1");
    }
}
