package com.todolist.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class WorkLogListDto {

    @JsonProperty
    private List<WorkLogDto> workLogList;

    public WorkLogListDto(List<WorkLogDto> workLogList) {
        this.workLogList = workLogList;
    }
}
