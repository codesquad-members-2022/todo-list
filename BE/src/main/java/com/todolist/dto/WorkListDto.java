package com.todolist.dto;

import java.util.List;
import lombok.Getter;

@Getter
public class WorkListDto {

    private Integer categoryId;
    private String categoryName;
    private List<WorkDto> works;

    public WorkListDto(Integer categoryId, String categoryName,
        List<WorkDto> works) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.works = works;
    }
}
