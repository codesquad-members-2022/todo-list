package com.todolist.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import java.time.LocalDateTime;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Work {

    private Integer id;
    private Integer categoryId;
    private String title;
    private String content;
    private String userId;
    private LocalDateTime createdDate;

    private Work() { }

    public Work(Integer id, Integer categoryId, String title, String content, String userId,
        LocalDateTime createdDate) {
        this.id = id;
        this.categoryId = categoryId;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.createdDate = createdDate;
    }
}
