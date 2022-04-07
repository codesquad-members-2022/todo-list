package com.todolist.domain;

import com.todolist.dto.WorkLogDto;
import java.time.LocalDateTime;

public class WorkLog {

    private String title;
    private Action action;
    private String previousColumn;
    private String changedColumn;
    private LocalDateTime updatedDateTime;

    private WorkLog() { }

    public WorkLog(String title, String action,
        String previousColumn, String changedColumn, LocalDateTime updatedDateTime) {
        this.title = title;
        this.action = Action.valueOf(action);
        this.previousColumn = previousColumn;
        this.changedColumn = changedColumn;
        this.updatedDateTime = updatedDateTime;
    }

    public WorkLogDto convertToDto() {
        return WorkLogDto.builder()
            .title(title)
            .action(action.getValue())
            .previousColumn(previousColumn)
            .changedColumn(changedColumn)
            .updatedDateTime(updatedDateTime)
            .build();
    }

    public enum Action {
        등록("등록"),
        삭제("삭제"),
        변경("변경"),
        이동("이동");

        private String value;

        Action(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
