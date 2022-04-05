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

}
