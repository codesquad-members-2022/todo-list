package codesquad.todo.service;

import codesquad.todo.domain.column.Column;
import codesquad.todo.domain.column.ColumnRepository;
import codesquad.todo.domain.work.Work;
import codesquad.todo.domain.work.WorkRepository;
import codesquad.todo.domain.work.WorkStatus;
import codesquad.todo.web.works.dto.WorkMoveRequest;
import codesquad.todo.web.works.dto.WorkMoveResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ColumnService {
    public static final String NOT_FOUND_WORK_ERROR = "해당 id의 Work를 찾을 수 없습니다.";

    private final ColumnRepository columnRepository;
    private final WorkRepository workRepository;

    public WorkMoveResponse moveWork(Long id, WorkMoveRequest requestDto) { // 변경전 id, 와 변경 위치값들
        Work originWork = findById(id);
        WorkStatus originStatus = originWork.getWorkStatus();

        Integer targetOrder = requestDto.getOrder();
        WorkStatus targetStatus = requestDto.getStatus();

        Column beforeColumn = columnRepository.findByStatus(originStatus);
        if(originStatus == targetStatus) {
            beforeColumn.moveWorkInColumn(originWork, targetOrder);
            columnRepository.updateColumn(beforeColumn, originStatus);
            return new WorkMoveResponse(originStatus, targetOrder);
        }

        Column afterColumn = columnRepository.findByStatus(targetStatus);

        beforeColumn.deleteWork(originWork);
        afterColumn.moveWorkInColumn(originWork, targetOrder);

        columnRepository.updateColumn(beforeColumn, originStatus);
        columnRepository.updateColumn(afterColumn, targetStatus);

        return new WorkMoveResponse(targetStatus, targetOrder);
    }

    public Work findById(Long id) {
        return workRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(NOT_FOUND_WORK_ERROR));
    }
}
