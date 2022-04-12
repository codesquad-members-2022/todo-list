package codesquad.todo.web.works.dto;

import codesquad.todo.domain.work.Work;
import codesquad.todo.domain.work.WorkStatus;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class WorkDetailResponse {

    private Long id;
    private String author;
    private String title;
    private String content;
    private WorkStatus workStatus;
    private Integer statusIndex;
    private LocalDateTime createDateTime;
    private LocalDateTime lastModifiedDateTime;

    public WorkDetailResponse(Work work) {
        this.id = work.getId();
        this.author = work.getAuthor().getName();
        this.title = work.getTitle();
        this.content = work.getContent();
        this.workStatus = work.getWorkStatus();
        this.statusIndex = work.getStatusIndex();
        this.createDateTime = work.getCreateDateTime();
        this.lastModifiedDateTime = work.getLastModifiedDateTime();
    }
}
