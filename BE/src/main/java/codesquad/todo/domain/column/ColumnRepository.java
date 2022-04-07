package codesquad.todo.domain.column;

import codesquad.todo.domain.work.WorkStatus;

public interface ColumnRepository {

    void updateColumn(Column column, WorkStatus workStatus);
    Column findByStatus(WorkStatus workStatus);
}
