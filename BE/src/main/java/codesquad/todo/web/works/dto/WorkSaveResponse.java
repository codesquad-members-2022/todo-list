package codesquad.todo.web.works.dto;

import lombok.Getter;

@Getter
public class WorkSaveResponse {

    private final Long workId;

    public WorkSaveResponse(Long workId) {
        this.workId = workId;
    }
}
