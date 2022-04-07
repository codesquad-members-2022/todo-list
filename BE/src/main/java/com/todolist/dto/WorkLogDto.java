package com.todolist.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class WorkLogDto {

    private String title;
    private String action;
    private String previousColumn;
    private String changedColumn;
    private LocalDateTime updatedDateTime;
}
