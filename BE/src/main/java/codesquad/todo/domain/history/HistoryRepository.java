package codesquad.todo.domain.history;

import java.util.List;

public interface HistoryRepository {
    Long save(History history);
    List<History> findAll(Long userId);
}
