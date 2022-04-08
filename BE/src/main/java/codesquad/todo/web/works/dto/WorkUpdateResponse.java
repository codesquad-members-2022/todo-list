package codesquad.todo.web.works.dto;

import codesquad.todo.domain.work.Work;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
public class WorkUpdateResponse {

    private String title;
    private String content;
    private LocalDateTime lastModifiedDateTime;

    public WorkUpdateResponse(Work work) {
        this.title = work.getTitle();
        this.content = work.getContent();
        this.lastModifiedDateTime = work.getLastModifiedDateTime();
    }
}
