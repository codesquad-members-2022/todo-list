package codesquad.todo.service;

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

@Slf4j
@Service
@RequiredArgsConstructor
public class WorkService {

    public static final String NOT_FOUND_WORK_ERROR = "해당 id의 Work를 찾을 수 없습니다.";

    private final WorkRepository workRepository;

    public WorkSaveResponse workSave(WorkSaveRequest workSaveRequest) {
        Work work = buildWork(workSaveRequest);
        workRepository.save(work);
        log.debug("[SAVE AFTER] : {}", work);
        return new WorkSaveResponse(work.getId());
    }

    private Work buildWork(WorkSaveRequest workSaveRequest) {
        LocalDateTime initTime = LocalDateTime.now();
        Work work = Work.builder()
                .title(workSaveRequest.getTitle())
                .content(workSaveRequest.getContent())
                .author(workSaveRequest.getAuthor())
                .workStatus(WorkStatus.TODO)
                .createTime(initTime)
                .lastModifiedDateTime(initTime)
                .build();
        return work;
    }

    public WorkUpdateResponse workUpdate(Long id, WorkUpdateRequest workUpdateRequest) {
        Work work = workRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(NOT_FOUND_WORK_ERROR));
        log.debug("[UPDATE BEFORE] : {}", work);
        work.update(workUpdateRequest.getTitle(), workUpdateRequest.getContent());
        workRepository.update(work);
        log.debug("[UPDATE AFTER] : {}", work);
        return new WorkUpdateResponse(work);
    }

    public WorkListResponse findAll() {
        return new WorkListResponse(workRepository.findAll());
    }

}
