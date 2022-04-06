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

    public Work(Integer categoryId, String title, String content, String userId,
        Integer deleteFlag, LocalDateTime createdDate) {
        this.categoryId = categoryId;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.deleteFlag = deleteFlag;
        this.createdDate = createdDate;
    }

    public Work(Integer id, Integer categoryId, String title, String content, String userId, LocalDateTime createdDate) {
        this.id = id;
        this.categoryId = categoryId;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.createdDate = createdDate;
    }

    public WorkDto convertToDto() {
        return new WorkDto(id, categoryId, title, content, userId, createdDate);
    }
}
