package codesquad.todo.domain.history;

import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class MemoryHistoryRepository implements HistoryRepository{

    private final Map<Long, History> store = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong();

    @Override
    public Long save(History history) {
        long historyId = sequence.incrementAndGet();
        history.initId(historyId);
        store.put(historyId, history);
        return historyId;
    }

    @Override
    public List<History> findAll(Long userId) {
        return store.values().stream()
                .filter(history -> history.isSameUserId(userId))
                .sorted(Comparator.comparing(History::getHistoryTime).reversed())
                .collect(Collectors.toList());
    }
}
