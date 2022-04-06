package com.todolist.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class WorkListDto {

    private String categoryName;
    private List<WorkDto> works;

    public WorkListDto(String categoryName, List<WorkDto> works) {
        this.categoryName = categoryName;
        this.works = works;
    }
}
