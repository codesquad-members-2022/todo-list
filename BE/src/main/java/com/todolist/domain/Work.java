package com.todolist.domain;

import com.todolist.dto.WorkDto;
import java.time.LocalDateTime;

public class Work {

    private Integer id;
    private Integer categoryId;
    private String title;
    private String content;
    private String userId;
    private Integer deleteFlag;
    private LocalDateTime createdDate;

    private Work() { }

    public Work(Integer id, Integer categoryId, String title, String content, LocalDateTime createdDate) {
        this.id = id;
        this.categoryId = categoryId;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
    }

    public WorkDto convertToDto() {
        return new WorkDto(id, categoryId, title, content, createdDate);
    }
}
