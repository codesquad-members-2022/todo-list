package codesquad.todo.domain.column;

import codesquad.todo.domain.work.Work;
import codesquad.todo.domain.work.WorkStatus;

import java.util.LinkedList;
import java.util.List;

public class Column {

    // column 식별 : columnStatus
    private WorkStatus columnStatus;
    private List<Work> works;

    public Column(WorkStatus columnStatus) {
        works = new LinkedList<>();
        this.columnStatus = columnStatus;
    }

    public boolean moveWorkInColumn(Work work, Integer order) {
        work.changeStatus(columnStatus);
        works.add(order, work);
        return true;
    }

    public void deleteWork(Work originWork) {
        works.remove(originWork);
    }
}
