package com.todolist.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class WorkDto {

    private Integer workId;
    private String title;
    private String content;

    @Builder
    public WorkDto(Integer workId, String title, String content) {
        this.workId = workId;
        this.title = title;
        this.content = content;
    }
}
