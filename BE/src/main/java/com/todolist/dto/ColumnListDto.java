package com.todolist.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class ColumnListDto {

    @JsonProperty
    private List<WorkListDto> workList;

    public ColumnListDto(List<WorkListDto> workList) {
        this.workList = workList;
    }
}
