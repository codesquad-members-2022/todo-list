package codesquad.todo.domain.work;

import java.util.LinkedList;
import java.util.List;

public class Column {
    private List<Work> works;
    private WorkStatus columnStatus;

    public Column(WorkStatus columnStatus) {
        works = new LinkedList<>();
        this.columnStatus = columnStatus;
    }

    void updateColumn(List<Work> updateWorks) {
        this.works = updateWorks;
    }
}
