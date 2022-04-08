package com.list.todo.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

@ApiModel
public class Task {
    @ApiModelProperty(hidden = true)
    private long idx;

    @ApiModelProperty(
            value = "태스크 제목",
            example = "제목 예시"
    )
    private String title;

    @ApiModelProperty(
            value = "태스크 내용",
            example = "내용 예시"
    )
    private String content;

    @ApiModelProperty(
            value = "작성자 닉네임",
            example = "sample"
    )
    private String authorNickname;

    @ApiModelProperty(
            value = "태스크 상태",
            example = "doing"
    )
    private String status;

    @ApiModelProperty(hidden = true)
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
