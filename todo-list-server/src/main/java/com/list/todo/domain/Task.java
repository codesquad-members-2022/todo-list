package com.list.todo.domain;

import java.time.LocalDateTime;

public class Task {
    private long idx;
    private String title;
    private String content;
    private String authorNickname;
    private String status;
    private LocalDateTime createAt;

    public Task(String title, String content, String authorNickname, String status) {
        this.title = title;
        this.content = content;
        this.authorNickname = authorNickname;
        this.status = status;
    }

    public void setIdx(long idx) {
        this.idx = idx;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public long getIdx() {
        return idx;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAuthorNickname() {
        return authorNickname;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }
}
