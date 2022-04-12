package codesquad.todo.domain.work;

import java.util.List;
import java.util.Optional;

public interface WorkRepository {
    Work save (Work work);
    Optional<Work> findById(Long id);
    void update(Work updateWork);
    void delete(Long id);
    List<Work> findByUserIdAndStatus(WorkStatus workStatus, Long userId);
    int maxStatusIndexOfWorks(Long userId, WorkStatus workStatus);
    List<Work> findAllWorkByUserId(Long userId);
    Optional<Work> findOne(Long userId, WorkStatus workStatus, Integer statusIndex);
}
