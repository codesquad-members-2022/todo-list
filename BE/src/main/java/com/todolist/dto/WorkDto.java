package com.todolist.dto;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class WorkDto {

    private Integer id;
    private String title;
    private String content;
    private LocalDateTime createdDateTime;

    public WorkDto(Integer id, String title, String content, LocalDateTime createdDateTime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdDateTime = createdDateTime;
    }
}
