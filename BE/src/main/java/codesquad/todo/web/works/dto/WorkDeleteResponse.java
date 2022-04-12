package codesquad.todo.web.works.dto;

import lombok.Getter;

@Getter
public class WorkDeleteResponse {

    private boolean isDeleted;
    private Long id;

    public WorkDeleteResponse(boolean isDeleted, Long id) {
        this.isDeleted = isDeleted;
        this.id = id;
    }
}
