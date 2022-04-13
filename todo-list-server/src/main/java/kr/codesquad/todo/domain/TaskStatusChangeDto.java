package kr.codesquad.todo.domain;

import io.swagger.annotations.ApiModelProperty;

public class TaskStatusChangeDto {
    @ApiModelProperty(
            value = "태스크 idx",
            example = "39"
    )
    private long idx;

    @ApiModelProperty(
            value = "변경할 status(변경 이후 값)",
            example = "1"
    )
    private int status;

    public TaskStatusChangeDto(long idx, int status) {
        this.idx = idx;
        this.status = status;
    }

    public long getIdx() {
        return idx;
    }

    public int getStatus() {
        return status;
    }
}
