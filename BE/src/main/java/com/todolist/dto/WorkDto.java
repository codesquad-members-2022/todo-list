package com.todolist.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import java.time.LocalDateTime;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class WorkDto {

    private Integer id;
    private Integer categoryId;
    private String title;
    private String content;
    private LocalDateTime createdDate;

    public WorkDto(Integer id, Integer categoryId, String title, String content, LocalDateTime createdDate) {
        this.id = id;
        this.categoryId = categoryId;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
    }
}
