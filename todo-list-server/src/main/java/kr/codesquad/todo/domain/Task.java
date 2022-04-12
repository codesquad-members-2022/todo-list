package kr.codesquad.todo.domain;

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
            value = "작성자 id",
            example = "sampleI"
    )
    private String author;

    @ApiModelProperty(
            value = "태스크 상태",
            example = "1"
    )
    private int status;

    @ApiModelProperty(hidden = true)
    private LocalDateTime createAt;

    public Task(String title, String content, String author, int status) {
        System.out.printf("title: %s, content: %s, author: %s, status: %d\n", title, content, author, status);
        this.title = title;
        this.content = content;
        this.author = author;
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

    public String getAuthor() {
        return author;
    }

    public int getStatus() {
        return status;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }
}
