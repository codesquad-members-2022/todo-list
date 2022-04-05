package codesquad.todo.domain.work;

import java.util.List;
import java.util.Optional;

public interface WorkRepository {
    Work save (Work work);
    Optional<Work> findById(Long id);
    void update(Work updateWork);
    void delete(Long id);
    List<Work> findAll();
    List<Work> findByStatus(WorkStatus workStatus);
}
