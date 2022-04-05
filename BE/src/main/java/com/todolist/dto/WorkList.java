package com.todolist.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.todolist.domain.Work;
import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class WorkList {

    private String categoryName;
    private List<Work> works;

    public WorkList(String categoryName, List<Work> works) {
        this.categoryName = categoryName;
        this.works = works;
    }
}
