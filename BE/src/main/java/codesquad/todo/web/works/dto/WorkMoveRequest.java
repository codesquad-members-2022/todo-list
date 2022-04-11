package codesquad.todo.web.works.dto;

import codesquad.todo.domain.work.WorkStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WorkMoveRequest {
    private WorkStatus status;
    private Long order;
}
