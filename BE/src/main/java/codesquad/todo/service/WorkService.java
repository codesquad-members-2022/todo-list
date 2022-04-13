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

    public WorkSaveResponse workSave(User user, WorkSaveRequest workSaveRequest) {
        Work work = buildWork(user, workSaveRequest);
        workRepository.save(work);
        log.debug("[SAVE AFTER] : {}", work);
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
        log.debug("[UPDATE AFTER] : {}", work);
        return new WorkUpdateResponse(work);
    }

    public WorkMoveResponse workMove(Long id, Integer targetStatusIndex, WorkStatus targetStatus) {
        Work originWork = findById(id);
        Long userId = originWork.getAuthor().getId();
        WorkStatus originStatus = originWork.getWorkStatus();
        Integer originStatusIndex = originWork.getStatusIndex();

        // 완전 같은 경우
        if (originStatus == targetStatus && originStatusIndex.equals(targetStatusIndex)) {
            return new WorkMoveResponse(originStatus, targetStatusIndex);
        }

//        Work changeWork = workRepository.findOne(userId, targetStatus, targetStatusIndex)
//                .orElseThrow(() -> new NoSuchElementException("일치하는 순서의 Work가 없습니다."));

        if(originStatus == targetStatus) {
            moveInSameStatus(originWork, targetStatusIndex);
            return new WorkMoveResponse(originStatus, targetStatusIndex);
        }

        moveToDifferentStatus(targetStatusIndex, targetStatus, originWork);
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

    private void detachFromOriginStatus(Work originWork, List<Work> originStatusWorks) {
        originStatusWorks.remove(originWork);
        originWork.injectstatusIndex(null);
        updateWorkIndex(originStatusWorks);
    }

    private void insertToTargetStatus(Integer targetStatusIndex, WorkStatus targetStatus, Work originWork) {
        Long userId = originWork.getAuthor().getId();
        originWork.changeStatus(targetStatus);
        originWork.injectstatusIndex(targetStatusIndex);
        workRepository.update(originWork);
    }

    private void insertTargetStatus(Integer targetStatusIndex, WorkStatus targetStatus, Work originWork, Long userId) {
        List<Work> targetStatusWorks = workRepository.findByUserIdAndStatus(targetStatus, userId);
        originWork.changeStatus(targetStatus);
        targetStatusWorks.add(targetStatusIndex, originWork);
        updateWorkIndex(targetStatusWorks);
        log.debug("targetStatusIndex : {}, originWork.getStatusIndex {}", targetStatusIndex, originWork.getStatusIndex());
    }

    private void moveToDifferentStatus(Integer targetStatusIndex, WorkStatus targetStatus, Work originWork) {
        Long userId = originWork.getAuthor().getId();
        WorkStatus originStatus = originWork.getWorkStatus();

        int countOfTargetStatus = workRepository.countOfStatus(userId, targetStatus);
        List<Work> originStatusWorks = workRepository.findByUserIdAndStatus(originStatus, userId);

        if(countOfTargetStatus == 0 && targetStatusIndex.equals(0)) {
            log.debug("[Move to empty status]");
            detachFromOriginStatus(originWork, originStatusWorks);
            insertToTargetStatus(targetStatusIndex, targetStatus, originWork);
            return;
        }

        log.debug("userId : {}, targetStatus : {}, targetStatusIndex {}", userId, targetStatus, targetStatusIndex);
        validateTargetStatusIndex(userId, targetStatus, targetStatusIndex);
        detachFromOriginStatus(originWork, originStatusWorks);
        insertTargetStatus(targetStatusIndex, targetStatus, originWork, userId);
    }

    private void validateTargetStatusIndex(Long userId, WorkStatus targetStatus, Integer targetStatusIndex) {
        if(workRepository.findOne(userId, targetStatus, targetStatusIndex).isEmpty()){
            throw new NoSuchElementException("일치하는 순서의 Work가 없습니다.");
        }
    }

    private void updateWorkIndex(List<Work> originStatusWorks) {
        for(int index = 0; index < originStatusWorks.size(); index++) {
            Work work = originStatusWorks.get(index);
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
        Long userId = work.getAuthor().getId();
        List<Work> originWorks = workRepository.findByUserIdAndStatus(work.getWorkStatus(), userId);
        detachFromOriginStatus(work, originWorks);
        work.delete();
        workRepository.update(work);
        return new WorkDeleteResponse(true, id);
    }
}
