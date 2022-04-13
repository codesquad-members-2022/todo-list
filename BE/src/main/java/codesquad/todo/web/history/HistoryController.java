package codesquad.todo.web.history;

import codesquad.todo.service.HistoryService;
import codesquad.todo.web.history.dto.HistoryListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/history")
public class HistoryController {

    private static final Long DEFAULT_USER_ID = 1L;
    private final HistoryService historyService;

    public HistoryListResponse allHistory() {
        return historyService.findAllHistory(DEFAULT_USER_ID);
    }
}
