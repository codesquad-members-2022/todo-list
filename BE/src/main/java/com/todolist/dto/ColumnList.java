package com.todolist.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.todolist.domain.Work;
import java.util.List;

public class ColumnList {

    @JsonProperty
    private List<WorkList> workList;

    public ColumnList(List<WorkList> workList) {
        this.workList = workList;
    }
}
