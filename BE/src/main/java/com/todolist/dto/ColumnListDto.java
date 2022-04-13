package com.todolist.dto;

import java.util.List;
import lombok.Getter;

@Getter
public class ColumnListDto {

    private String userId;
    private List<WorkListDto> workList;

    public ColumnListDto(String userId, List<WorkListDto> workList) {
        this.userId = userId;
        this.workList = workList;
    }
}
