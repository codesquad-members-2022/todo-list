package com.todolist.dto;

import java.util.List;
import lombok.Getter;

@Getter
public class WorkLogListDto {

    private String userId;
    private List<WorkLogDto> workLogList;

    public WorkLogListDto(String userId, List<WorkLogDto> workLogList) {
        this.userId = userId;
        this.workLogList = workLogList;
    }
}
