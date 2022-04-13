package codesquad.todo.web.works.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor @AllArgsConstructor
public class WorkUpdateRequest {
    private String title;
    private String content;
}
