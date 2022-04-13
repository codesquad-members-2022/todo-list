package codesquad.todo.domain.work;

import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class MemoryWorkRepository implements WorkRepository {

    private final Map<Long, Work> store = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong();

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

    @Override
    public void update(Work updateWork) {
        store.put(updateWork.getId(), updateWork);
    }

    @Override
    public List<Work> findAllWorkByUserId(Long userId) {
        return store.values().stream()
                .filter(work -> work.getAuthor().isSameId(userId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Work> findByUserIdAndStatus(WorkStatus workStatus, Long userId) {
        return store.values()
                .stream()
                .filter(work -> work.getAuthor().isSameId(userId))
                .filter(work -> work.hasSameStatus(workStatus))
                .sorted(Comparator.comparing(Work::getStatusIndex))
                .collect(Collectors.toList());
    }

    @Override
    public int countOfStatus(Long userId, WorkStatus workStatus) {
        return (int)store.values()
                .stream()
                .filter(work -> work.getAuthor().isSameId(userId))
                .filter(work -> work.hasSameStatus(workStatus))
                .count();
    }

    @Override
    public Optional<Work> findOne(Long userId, WorkStatus workStatus, Integer statusIndex) {
        return store.values()
                .stream()
                .filter(work -> work.getAuthor().isSameId(userId))
                .filter(work -> work.hasSameStatus(workStatus))
                .filter(work -> work.isSameStatusIndex(statusIndex))
                .findAny();
    }


}
