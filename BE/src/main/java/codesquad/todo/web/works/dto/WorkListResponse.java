package codesquad.todo.web.works.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class WorkListResponse {

    private final List<WorkDetailResponse> works;

    public WorkListResponse(List<WorkDetailResponse> workDetails) {
        this.works = workDetails;
    }
}
