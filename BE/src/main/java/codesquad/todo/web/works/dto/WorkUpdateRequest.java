package codesquad.todo.web.works.dto;

import lombok.Getter;

@Getter
public class WorkUpdateRequest {
    private String title;
    private String content;

    public WorkUpdateRequest() {
    }

    public WorkUpdateRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
