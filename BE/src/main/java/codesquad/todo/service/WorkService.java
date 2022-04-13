package codesquad.todo.service;

import codesquad.todo.domain.user.User;
import codesquad.todo.domain.work.Work;
import codesquad.todo.domain.work.WorkRepository;
import codesquad.todo.domain.work.WorkStatus;
import codesquad.todo.web.works.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class WorkService {

    public static final String NOT_FOUND_WORK_ERROR = "해당 id의 Work를 찾을 수 없습니다.";

    private final WorkRepository workRepository;
    private final HistoryService historyService;

    public WorkSaveResponse workSave(User user, WorkSaveRequest workSaveRequest) {
        Work work = buildWork(user, workSaveRequest);
        workRepository.save(work);
        log.debug("[SAVE AFTER] : {}", work);
        historyService.saveCreateHistory(work);
        return new WorkSaveResponse(work.getId());
    }

    private Work buildWork(User user, WorkSaveRequest workSaveRequest) {
        LocalDateTime initTime = LocalDateTime.now();
        Integer nextStatusIndex = workRepository.countOfStatus(user.getId(), WorkStatus.TODO);

        Work work = Work.builder()
                .title(workSaveRequest.getTitle())
                .content(workSaveRequest.getContent())
                .author(user)
                .workStatus(WorkStatus.TODO)
                .statusIndex(nextStatusIndex)
                .createDateTime(initTime)
                .lastModifiedDateTime(initTime)
                .build();

        return work;
    }

    public Work findById(Long id) {
        return workRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(NOT_FOUND_WORK_ERROR));
    }

    public WorkUpdateResponse workUpdate(Long workId, WorkUpdateRequest workUpdateRequest) {
        Work work = workRepository.findById(workId)
                .orElseThrow(() -> new NoSuchElementException(NOT_FOUND_WORK_ERROR));
        log.debug("[UPDATE BEFORE] : {}", work);
        work.update(workUpdateRequest.getTitle(), workUpdateRequest.getContent());
        workRepository.update(work);
        historyService.saveUpdateHistory(work);
        log.debug("[UPDATE AFTER] : {}", work);
        return new WorkUpdateResponse(work);
    }

    public WorkMoveResponse workMove(Long id, Integer targetStatusIndex, WorkStatus targetStatus) {
        Work originWork = findById(id);
        WorkStatus originStatus = originWork.getWorkStatus();
        Integer originStatusIndex = originWork.getStatusIndex();

        // 완전 같은 경우
        if (originStatus == targetStatus && originStatusIndex.equals(targetStatusIndex)) {
            return new WorkMoveResponse(originStatus, targetStatusIndex);
        }

        // 같은 status, 다른 순서
        if(originStatus == targetStatus) {
            moveInSameStatus(originWork, targetStatusIndex);
            return new WorkMoveResponse(originStatus, targetStatusIndex);
        }

        // 다른 status로 이동
        moveToDifferentStatus(targetStatusIndex, targetStatus, originWork);
        historyService.saveMoveHistory(originWork, originStatus, targetStatus);
        return new WorkMoveResponse(targetStatus, targetStatusIndex);
    }

    private void moveInSameStatus(Work originWork, Integer targetStatusIndex) {
        Work changeWork = workRepository.findOne(originWork.getAuthor().getId(), originWork.getWorkStatus(), targetStatusIndex)
                .orElseThrow(() -> new NoSuchElementException("일치하는 순서의 Work가 없습니다."));

        Integer originStatusIndex = originWork.getStatusIndex();
        changeWork.injectstatusIndex(originStatusIndex);
        originWork.injectstatusIndex(targetStatusIndex);
        workRepository.update(originWork);
        workRepository.update(changeWork);
    }

    // 다른 status로 이동
    private void moveToDifferentStatus(Integer targetStatusIndex, WorkStatus targetStatus, Work originWork) {
        Long userId = originWork.getAuthor().getId();
        validateTargetStatusIndex(userId, targetStatus, targetStatusIndex); // 유효한 인덱스인지 검증
        detachFromOriginStatus(originWork); // originStatus에서 분리
        insertToTargetStatus(targetStatusIndex, targetStatus, originWork); // targetStatus에 삽입
    }

    // 유효하지 않은 범위의 인덱스로 이동하려 요청했을 때, 예외 발생
    private void validateTargetStatusIndex(Long userId, WorkStatus targetStatus, Integer targetStatusIndex) {
        int countOfTargetStatus = workRepository.countOfStatus(userId, targetStatus);
        if (targetStatusIndex <0 || targetStatusIndex > countOfTargetStatus) {
            throw new IllegalStateException(
                    String.format("옮기고자하는 곳의 인덱스 유효범위는 %d 이상 %d 이하입니다.", 0, countOfTargetStatus)
            );
        }
    }

    private void detachFromOriginStatus(Work originWork) {
        List<Work> originStatusWorks = workRepository
                .findByUserIdAndStatus(originWork.getWorkStatus(), originWork.getAuthor().getId());

        originStatusWorks.remove(originWork);
        originWork.injectstatusIndex(null);
        updateWorkIndex(originStatusWorks);
    }

    private void insertToTargetStatus(Integer targetStatusIndex, WorkStatus targetStatus, Work originWork) {
        List<Work> targetStatusWorks = workRepository.findByUserIdAndStatus(targetStatus, originWork.getAuthor().getId());
        originWork.changeStatus(targetStatus);
        targetStatusWorks.add(targetStatusIndex, originWork);
        updateWorkIndex(targetStatusWorks);
    }

    private void updateWorkIndex(List<Work> works) {
        for(int index = 0; index < works.size(); index++) {
            Work work = works.get(index);
            work.injectstatusIndex(index);
            workRepository.update(work);
        }
    }

    public WorkListResponse findWorkList(Long userId) {
        // 레포지토리에서, 작성자가 userId 값인 Work들을 가져와 반환
        List<WorkDetailResponse> workDetails = workRepository.findAllWorkByUserId(userId).stream()
                .map(WorkDetailResponse::new)
                .collect(Collectors.toList());
        return new WorkListResponse(workDetails);
    }

    public WorkDeleteResponse workDelete(Long id) {
        Work work = findById(id);
        WorkStatus originStatus = work.getWorkStatus();

        detachFromOriginStatus(work);

        work.delete();
        workRepository.update(work);
        historyService.saveDeleteHistory(work, originStatus);
        return new WorkDeleteResponse(true, id);
    }
}
