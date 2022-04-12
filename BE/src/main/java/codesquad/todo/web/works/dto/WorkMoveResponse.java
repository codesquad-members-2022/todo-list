package codesquad.todo.web.works.dto;

import codesquad.todo.domain.work.WorkStatus;
import lombok.Getter;

@Getter
public class WorkMoveResponse {
    private Integer order;
    private WorkStatus status;

    public WorkMoveResponse(WorkStatus status, Integer order) {
        this.status = status;
        this.order = order;
    }
}
