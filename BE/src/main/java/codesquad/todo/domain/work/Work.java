package codesquad.todo.domain.work;


import lombok.Builder;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
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

    private Work(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.content = builder.content;
        this.author = builder.author;
        this.workStatus = builder.workStatus;
        this.createDateTime = builder.createDateTime;
        this.lastModifiedDateTime = builder.lastModifiedDateTime;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String title;
        private String content;
        private String author;
        private WorkStatus workStatus;
        private LocalDateTime createDateTime;
        private LocalDateTime lastModifiedDateTime;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Builder author(String author) {
            this.author = author;
            return this;
        }

        public Builder workStatus(WorkStatus status) {
            this. workStatus = status;
            return this;
        }

        public Builder createTime(LocalDateTime createDateTime) {
            this.createDateTime = createDateTime;
            return this;
        }

        public Builder lastModifiedDateTime(LocalDateTime lastModifiedDateTime) {
            this.lastModifiedDateTime = lastModifiedDateTime;
            return this;
        }

        public Work build() {
            return new Work(this);
        }
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

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public WorkStatus getWorkStatus() {
        return workStatus;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public LocalDateTime getLastModifiedDateTime() {
        return lastModifiedDateTime;
    }
}
