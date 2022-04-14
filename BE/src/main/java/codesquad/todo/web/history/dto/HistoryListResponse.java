package codesquad.todo.web.history.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class HistoryListResponse {

    private final List<HistoryDetailResponse> histories;
}
