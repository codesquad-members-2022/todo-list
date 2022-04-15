package codesquad.todo.service;

import codesquad.todo.domain.user.User;
import codesquad.todo.domain.user.UserRepository;
import codesquad.todo.domain.work.Work;
import codesquad.todo.domain.work.WorkStatus;
import codesquad.todo.web.works.dto.WorkMoveRequest;
import codesquad.todo.web.works.dto.WorkSaveRequest;
import codesquad.todo.web.works.dto.WorkSaveResponse;
import codesquad.todo.web.works.dto.WorkUpdateRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class WorkServiceTest {

    @Autowired
    private WorkService workService;
    @Autowired
    private UserRepository userRepository;

    User testUser;

    @BeforeEach
    void init() {
        testUser = new User("test");
        userRepository.save(testUser);
    }

    @Test
    public void workSaveTest() {
        // given
        WorkSaveRequest request = new WorkSaveRequest("제목1", "내용1");

        // when
        WorkSaveResponse response = workService.workSave(testUser, request);
        Long workId = response.getWorkId();
        Work findWork = workService.findById(workId);

        // then
        assertThat(findWork.getId()).isEqualTo(workId);
        assertThat(findWork.getTitle()).isEqualTo("제목1");
        assertThat(findWork.getContent()).isEqualTo("내용1");
        assertThat(findWork.getWorkStatus()).isSameAs(WorkStatus.TODO);
        assertThat(findWork.getStatusIndex()).isSameAs(0);
    }

    @Test
    public void updateTest() {
        // given
        WorkSaveRequest saveRequest = new WorkSaveRequest("제목1", "내용1");
        WorkSaveResponse saveResponse = workService.workSave(testUser, saveRequest);
        Long workId = saveResponse.getWorkId();

        // when
        WorkUpdateRequest updateRequest = new WorkUpdateRequest("변경제목", "변경내용");
        workService.workUpdate(workId, updateRequest);
        Work findWork = workService.findById(workId);

        // then
        assertThat(findWork.getTitle()).isEqualTo("변경제목");
        assertThat(findWork.getContent()).isEqualTo("변경내용");
    }

    @Test
    public void move_Same_Status_Same_Index_Test() {
        // given
        WorkSaveRequest saveRequest = new WorkSaveRequest("제목1", "내용1");
        WorkSaveResponse saveResponse = workService.workSave(testUser, saveRequest);
        WorkMoveRequest request = new WorkMoveRequest(WorkStatus.TODO, 0);

        // when
        workService.workMove(saveResponse.getWorkId(), request.getTargetStatusIndex(), request.getTargetStatus());
        Long workId = saveResponse.getWorkId();
        Work findWork = workService.findById(workId);

        // then
        assertThat(findWork.getWorkStatus()).isEqualTo(WorkStatus.TODO);
        assertThat(findWork.getStatusIndex()).isEqualTo(0);
    }

    @Test
    public void move_Same_Status_Other_Index_Test() {
        // given
        WorkSaveRequest saveRequest1 = new WorkSaveRequest("제목1", "내용1");
        WorkSaveRequest saveRequest2 = new WorkSaveRequest("제목2", "내용2");
        WorkSaveResponse saveResponse1 = workService.workSave(testUser, saveRequest1);
        WorkSaveResponse saveResponse2 = workService.workSave(testUser, saveRequest2);

        // when
        workService.workMove(saveResponse2.getWorkId(), 0, WorkStatus.TODO);
        Work work1 = workService.findById(saveResponse1.getWorkId());
        Work work2 = workService.findById(saveResponse2.getWorkId());

        // then
        assertThat(work1.getStatusIndex()).isEqualTo(1);
        assertThat(work2.getStatusIndex()).isEqualTo(0);
    }

    @Test
    public void move_Same_Status_Invalid_Index_Test() {
        // given
        WorkSaveRequest saveRequest1 = new WorkSaveRequest("제목1", "내용1");
        WorkSaveResponse saveResponse1 = workService.workSave(testUser, saveRequest1);

        // when
        Work work1 = workService.findById(saveResponse1.getWorkId());

        // then
        assertThatThrownBy(() -> workService.workMove(saveResponse1.getWorkId(), 7, WorkStatus.TODO))
                .isInstanceOf(NoSuchElementException.class);
    }

    @AfterEach
    void tearDown() {
        testUser = null;
    }
}
