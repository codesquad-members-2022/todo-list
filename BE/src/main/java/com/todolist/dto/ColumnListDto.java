package com.todolist.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ColumnListDto {

    private String userId;
    private List<WorkListDto> workList;

    public ColumnListDto(String userId, List<WorkListDto> workList) {
        this.userId = userId;
        this.workList = workList;
    }
}
