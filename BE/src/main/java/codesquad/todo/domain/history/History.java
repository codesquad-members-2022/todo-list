package codesquad.todo.domain.history;

import codesquad.todo.domain.work.Work;
import codesquad.todo.domain.work.WorkStatus;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class History {
    private Long id;
    private Work work;
    private HistoryType historyType;
    private LocalDateTime historyTime;
    private WorkStatus beforeStatus;
    private WorkStatus currentStatus;

    public HistoryType getHistoryType() {
        return historyType;
    }

    public boolean isSameUserId(Long id) {
        return work.getAuthor().getId().equals(id);
    }

    public void initId(long historyId) {
        this.id = historyId;
    }

    public Long getId() {
        return id;
    }

    public String getAuthorName() {
        return work.getAuthor().getName();
    }

    public String getTitle() {
        return work.getTitle();
    }

    public LocalDateTime getHistoryTime() {
        return historyTime;
    }

    public WorkStatus getBeforeStatus() {
        return beforeStatus;
    }

    public WorkStatus getAfterStatus() {
        return currentStatus;
    }

    public Long getWorkId() {
        return work.getId();
    }

    public String getBeforeStatusName() {
        return (beforeStatus == null) ? null : beforeStatus.name();
    }

    public String getCurrentStatusName() {
        return (currentStatus == null) ? null : currentStatus.name();
    }

    public String getHistoryTypeName() {
        return (historyType == null) ? null : historyType.name();
    }
}
