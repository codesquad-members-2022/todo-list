package com.todolist.domain;

import com.todolist.dto.WorkDto;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class Work {

    private Integer id;
    private Integer categoryId;
    private String title;
    private String content;
    private String userId;
    private boolean deleteFlag;
    private LocalDateTime createdDateTime;

    private Work() { }

    public Work(Integer categoryId, String title, String content, String userId) {
        this.categoryId = categoryId;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.deleteFlag = false;
        this.createdDateTime = LocalDateTime.now();
    }

    public Work(Integer id, String title, String content, LocalDateTime createdDateTime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdDateTime = createdDateTime;
    }

    public WorkDto convertToDtoForCreation(Integer id) {
        return WorkDto.builder()
            .id(id)
            .title(title)
            .content(content)
            .createdDateTime(createdDateTime)
            .build();
    }

    public WorkDto convertToDto() {
        return WorkDto.builder()
            .id(id)
            .title(title)
            .content(content)
            .createdDateTime(createdDateTime)
            .build();
    }
}
