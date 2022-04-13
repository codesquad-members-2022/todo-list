package codesquad.todo.web.works.dto;

import codesquad.todo.domain.work.WorkStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor @AllArgsConstructor
public class WorkMoveRequest {
    private WorkStatus targetStatus;
    private Integer targetStatusIndex;
}
