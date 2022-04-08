package codesquad.todo.web.works.dto;

import codesquad.todo.domain.work.Work;
import lombok.Getter;

import java.util.List;

@Getter
public class WorkListResponse {

    private final List<Work> works;

    public WorkListResponse(List<Work> works) {
        this.works = works;
    }
}
