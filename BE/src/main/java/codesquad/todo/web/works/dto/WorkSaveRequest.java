package codesquad.todo.web.works.dto;

import lombok.Getter;

@Getter
public class WorkSaveRequest {

    private String title;
    private String content;

    public WorkSaveRequest() {}
}
