package com.todolist.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ColumnListDto {

    private String userId;
    private List<WorkListDto> workList;
}
