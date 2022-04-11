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
import java.util.NoSuchElementException;

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
        Long nextStatusOrder = workRepository.maxStatusOrderOfWorks(user.getId(), WorkStatus.TODO) + 1;

        Work work = Work.builder()
                .title(workSaveRequest.getTitle())
                .content(workSaveRequest.getContent())
                .author(user)
                .workStatus(WorkStatus.TODO)
                .statusOrder(nextStatusOrder)
                .createTime(initTime)
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

    public WorkMoveResponse workMove(Long id, WorkMoveRequest requestDto) {
        Work originWork = findById(id);
        WorkStatus originStatus = originWork.getWorkStatus();
        Long originStatusOrder = originWork.getStatusOrder();

        WorkStatus targetStatus = requestDto.getStatus();
        Long targetStatusOrder = requestDto.getOrder();

        if (originStatus == targetStatus && originStatusOrder.equals(targetStatusOrder)) {
            return new WorkMoveResponse(originStatus, targetStatusOrder);
        }

        if(originStatus == targetStatus) {
            // TODO : 지정 타겟번호에 위치한 Work와 statusOrder값을 바꾸고, DB에 반영
            return new WorkMoveResponse(originStatus, targetStatusOrder);
        }
        // TODO: Status 상태 변경시 기존 status 기준으로 다른 Work들에도 statusOrder 반영

        // TODO: 옮겨간 status 기준으로 다른 Work들에도 StatusOrder 변경 반영이 필요

        return new WorkMoveResponse(targetStatus, targetStatusOrder);
    }

    public WorkListResponse findWorkListByStatus(Long userId, WorkStatus workStatus) {
        // 레포지토리에서, 작성자가 userId 값이고 status가 workStatus인 Work들을 가져와 반환


        return new WorkListResponse(workRepository.findAll());
    }

}
