package com.todolist.domain;

import com.todolist.dto.WorkDto;
import java.time.LocalDateTime;

public class Work {

    private Integer id;
    private Integer categoryId;
    private String title;
    private String content;
    private String userId;
    private boolean deleteFlag;
    private LocalDateTime createdDateTime;

    private Work() { }

    public Work(Integer id, Integer categoryId, String title, String content, String userId,
        boolean deleteFlag) {
        this.id = id;
        this.categoryId = categoryId;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.deleteFlag = deleteFlag;
        this.createdDateTime = LocalDateTime.now();
    }

    public Work(Integer id, Integer categoryId, String title, String content, LocalDateTime createdDateTime) {
        this.id = id;
        this.categoryId = categoryId;
        this.title = title;
        this.content = content;
        this.createdDateTime = createdDateTime;
    }

    public WorkDto convertToDto() {
        return WorkDto.builder()
            .id(id)
            .categoryId(categoryId)
            .title(title)
            .content(content)
            .createdDateTime(createdDateTime)
            .build();
    }

    public Integer getId() {
        return id;
    }
}
