package codesquad.todo.web.history.dto;

import codesquad.todo.domain.history.History;
import codesquad.todo.domain.history.HistoryType;
import codesquad.todo.domain.work.WorkStatus;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class HistoryDetailResponse {
    private Long id;
    private String author;
    private String title;
    private HistoryType historyType;
    private LocalDateTime historyTime;
    private WorkStatus beforeStatus;
    private WorkStatus afterStatus;

    public HistoryDetailResponse(History history) {
        this.id = history.getId();
        this.author = history.getAuthorName();
        this.title = history.getTitle();
        this.historyType = history.getHistoryType();
        this.historyTime = history.getHistoryTime();
        this.beforeStatus = history.getBeforeStatus();
        this.afterStatus = history.getAfterStatus();
    }
}
