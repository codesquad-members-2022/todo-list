package com.hooria.todo.domain;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class Card {

    private long id;
    private int status;
    private String title;
    private String content;
    private String userId;
    private int applianceInfo;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private boolean deletedYn;
    private int index;

    public Card(long id, int status, String title, String content, String userId, int applianceInfo,
        LocalDateTime createdAt, LocalDateTime modifiedAt, boolean deletedYn, int index) {
        this.id = id;
        this.status = status;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.applianceInfo = applianceInfo;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.deletedYn = deletedYn;
        this.index = index;
    }

    public Card(int status, String title, String content, String userId, int applianceInfo, int index) {
        this.status = status;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.applianceInfo = applianceInfo;
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
        this.deletedYn = false;
        this.index = index;
    }
}
