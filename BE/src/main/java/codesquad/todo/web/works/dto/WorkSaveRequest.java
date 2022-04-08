package codesquad.todo.web.works.dto;

import lombok.Getter;

@Getter
public class WorkSaveRequest {

    private String title;
    private String content;
    private String author;

    public WorkSaveRequest() {}

    public WorkSaveRequest(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
