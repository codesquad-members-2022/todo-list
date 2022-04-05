package codesquad.todo.domain.work;


import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Work {

    private Long id;
    private String title;
    private String content;
    private String author;
    private WorkStatus workStatus;
    private LocalDateTime createDateTime;
    private LocalDateTime lastModifiedDateTime;

    public Work(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.workStatus = WorkStatus.TODO;
        this.createDateTime = LocalDateTime.now();
        this.lastModifiedDateTime = createDateTime;
    }

    public void initId(Long id) {
        this.id = id;
    }

    public boolean hasSameStatus(WorkStatus workStatus) {
        return this.workStatus == workStatus;
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
