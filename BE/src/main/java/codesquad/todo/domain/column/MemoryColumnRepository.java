package codesquad.todo.domain.column;

import codesquad.todo.domain.work.WorkStatus;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MemoryColumnRepository implements ColumnRepository {

    private Map<WorkStatus, Column> columns;

    public MemoryColumnRepository() {
        columns = new ConcurrentHashMap<>();
        columns.put(WorkStatus.TODO, new Column(WorkStatus.TODO));
        columns.put(WorkStatus.PROGRESS, new Column(WorkStatus.PROGRESS));
        columns.put(WorkStatus.DONE, new Column(WorkStatus.DONE));
    }

    @Override
    public void updateColumn(Column column, WorkStatus workStatus) {
        columns.put(workStatus, column);
    }

    @Override
    public Column findByStatus(WorkStatus workStatus) {
        return columns.get(workStatus);
    }
}
