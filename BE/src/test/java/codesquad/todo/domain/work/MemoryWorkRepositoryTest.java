package codesquad.todo.domain.work;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryWorkRepositoryTest {

    private MemoryWorkRepository memoryWorkRepository;

    @BeforeEach
    void setUp() {
        this.memoryWorkRepository = new MemoryWorkRepository();
    }

    @Test
    @DisplayName("저장한 work의 id로 조회하면 같은 work가 조회되어야한다.")
    public void saveTest() {
        //given
        Work work = new Work("workTitle", "호눅스랑 놀기", "샤인" );

        //when
        Work saveWork = memoryWorkRepository.save(work);
        Work findWork = memoryWorkRepository.findById(saveWork.getId()).get();

        //then
        assertThat(findWork).isSameAs(saveWork);
    }
    
    @Test
    @DisplayName("update 메서드 호출시 내용이 UpdateWork로 변경 되야한다.")
    public void updateTest() throws Exception {
        //given
        Work originalWork = new Work("테스트", "테스트 content", "tester");
        memoryWorkRepository.save(originalWork);
        Long saveId = originalWork.getId();
        Work updateWork = memoryWorkRepository.findById(saveId).get();
        updateWork.update("변경후", "변경 컨텐츠");

        //when
        memoryWorkRepository.update(updateWork);

        //then
        Work changeWork = memoryWorkRepository.findById(saveId).get();
        assertThat(changeWork.getId()).isEqualTo(saveId);
        assertThat(changeWork.getTitle()).isEqualTo("변경후");
    }

    @Test
    @DisplayName("delete 메서드 호출시 Work 삭제가 정상적으로 되어야 한다.")
    public void deleteTests() {
        // given
        Work originalWork = new Work("테스트", "테스트 content", "tester");
        memoryWorkRepository.save(originalWork);
        Long originalWorkId = originalWork.getId();

        // when
        memoryWorkRepository.delete(originalWorkId);

        // then
        Optional<Work> findWork = memoryWorkRepository.findById(originalWorkId);
        assertThat(findWork.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("findAll 메서드 호출시 모든 저장된 Works가 조회되어야 한다.")
    public void findAllTest() {
        // given
        Work originalWork1 = new Work("테스트1", "테스트 content1", "tester1");
        Work originalWork2 = new Work("테스트2", "테스트 content2", "tester2");
        memoryWorkRepository.save(originalWork1);
        memoryWorkRepository.save(originalWork2);

        // when
        List<Work> works = memoryWorkRepository.findAll();

        // then
        assertThat(works.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("findByStatus 메서드 호출시 해당 상태의 Works를 찾아와야 한다.")
    public void findByStatusTest() {
        // given
        Work originalWork1 = new Work("테스트1", "테스트 content1", "tester1");
        Work originalWork2 = new Work("테스트2", "테스트 content2", "tester2");
        memoryWorkRepository.save(originalWork1);
        memoryWorkRepository.save(originalWork2);

        // when
        originalWork1.changeStatus(WorkStatus.DONE);
        List<Work> works = memoryWorkRepository.findByStatus(WorkStatus.TODO);

        // then
        assertThat(works.size()).isEqualTo(1);
    }
}
