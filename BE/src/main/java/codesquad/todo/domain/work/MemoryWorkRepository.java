package codesquad.todo.domain.work;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class MemoryWorkRepository implements WorkRepository {
    private final Map<Long, Work> store;
    private final AtomicLong sequence;

    public MemoryWorkRepository() {
        this.store = new ConcurrentHashMap<>();
        sequence = new AtomicLong();
    }

    @Override
    public Work save(Work work) {
        Long id = sequence.incrementAndGet();
        work.initId(id);
        store.put(id, work);
        return work;
    }

    @Override
    public Optional<Work> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public void update(Work updateWork) {
        store.put(updateWork.getId(), updateWork);
    }

    @Override
    public void delete(Long id) {
        store.remove(id);
    }

    @Override
    public List<Work> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public List<Work> findByStatus(WorkStatus workStatus) {
        return store.values()
                .stream()
                .filter(work -> work.hasSameStatus(workStatus))
                .collect(Collectors.toList());
    }
}
