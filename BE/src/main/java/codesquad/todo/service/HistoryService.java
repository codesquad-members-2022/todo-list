package codesquad.todo.service;

import codesquad.todo.domain.history.History;
import codesquad.todo.domain.history.HistoryRepository;
import codesquad.todo.domain.history.HistoryType;
import codesquad.todo.domain.work.Work;
import codesquad.todo.domain.work.WorkStatus;
import codesquad.todo.web.history.dto.HistoryDetailResponse;
import codesquad.todo.web.history.dto.HistoryListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class HistoryService {

    private final HistoryRepository historyRepository;

    public void saveCreateHistory(Work work){
        History createHistory = History.builder()
                .work(work)
                .historyTime(work.getCreateDateTime())
                .historyType(HistoryType.CREATED)
                .beforeStatus(null)
                .currentStatus(WorkStatus.TODO)
                .build();;
        historyRepository.save(createHistory);
    }

    public void saveUpdateHistory(Work work) {
        History updateHistory = History.builder()
                .work(work)
                .historyTime(work.getLastModifiedDateTime())
                .historyType(HistoryType.MODIFIED)
                .beforeStatus(work.getWorkStatus())
                .currentStatus(work.getWorkStatus())
                .build();
        historyRepository.save(updateHistory);
    }

    public void saveMoveHistory(Work work, WorkStatus originStatus, WorkStatus targetStatus) {
        History moveHistory = History.builder()
                .work(work)
                .historyTime(work.getLastModifiedDateTime())
                .historyType(HistoryType.MOVED)
                .beforeStatus(originStatus)
                .currentStatus(targetStatus)
                .build();
        historyRepository.save(moveHistory);
    }

    public void saveDeleteHistory(Work work, WorkStatus originStatus) {
        History deleteHistory = History.builder()
                .work(work)
                .historyTime(work.getLastModifiedDateTime())
                .historyType(HistoryType.DELETED)
                .beforeStatus(originStatus)
                .currentStatus(null)
                .build();
        historyRepository.save(deleteHistory);
    }

    public HistoryListResponse findAllHistory(Long userId) {
        List<HistoryDetailResponse> historyDetails = historyRepository.findAll(userId).stream()
                .map(HistoryDetailResponse::new)
                .collect(Collectors.toList());
        return new HistoryListResponse(historyDetails);
    }
}
