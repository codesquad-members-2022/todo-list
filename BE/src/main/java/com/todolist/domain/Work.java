package com.todolist.domain;

import com.todolist.dto.ModifiedWorkDto;
import com.todolist.dto.WorkDto;
import java.time.LocalDateTime;
import lombok.Builder;
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

    @Builder
    public Work(Integer id, Integer categoryId, String title, String content, String userId,
        boolean deleteFlag, LocalDateTime createdDateTime) {
        this.id = id;
        this.categoryId = categoryId;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.deleteFlag = deleteFlag;
        this.createdDateTime = createdDateTime;
    }

    public WorkDto convertToDto() {
        return new WorkDto(id, title, content);
    }

    public WorkDto convertToDtoForCreation(Integer id) {
        return new WorkDto(id, title, content);
    }

    public ModifiedWorkDto convertToDtoForModification() {
        return new ModifiedWorkDto(title, content);
    }
}
