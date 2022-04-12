package codesquad.todo.domain.work;

import codesquad.todo.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Builder @ToString
@Getter
public class Work {

    private Long id;
    private String title;
    private String content;
    private User author;
    private WorkStatus workStatus;
    private Integer statusIndex;
    private LocalDateTime createDateTime;
    private LocalDateTime lastModifiedDateTime;

    public boolean isSameStatusIndex(Integer statusIndex) {
        return this.statusIndex.equals(statusIndex);
    }

    public void initId(Long id) {
        this.id = id;
    }

    public boolean hasSameStatus(WorkStatus workStatus) {
        return this.workStatus == workStatus;
    }

    public void injectstatusIndex(Integer statusIndex) {
        this.statusIndex = statusIndex;
    }

    public void update(String updateTitle, String updateContent) {
        this.title = updateTitle;
        this.content = updateContent;
        this.lastModifiedDateTime = LocalDateTime.now();
    }

    public void changeStatus(WorkStatus workStatus) {
        this.workStatus = workStatus;
        this.lastModifiedDateTime = LocalDateTime.now();
    }
}
