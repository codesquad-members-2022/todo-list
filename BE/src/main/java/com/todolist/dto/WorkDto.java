package com.todolist.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class WorkDto {

    private Integer id;
    private String title;
    private String content;
    private LocalDateTime createdDateTime;
}
