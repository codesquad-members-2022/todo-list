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
}
//writer:String
//title: String
//beforeStatus: CardStatus
//afterStatus: CardStatus?
//action: CardAction
//date: Date
//history 타입입니다
//cardStatus는 todo인지doing인지done인지
//string으로 주시면 될듯해요!
