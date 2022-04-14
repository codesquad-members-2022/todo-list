package com.todolist.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class WorkLogListDto {

    private String userId;
    private List<WorkLogDto> workLogList;
}
