package codesquad.todo.service;

import codesquad.todo.domain.work.Work;
import codesquad.todo.domain.work.WorkRepository;
import codesquad.todo.web.works.dto.BaseResponse;
import codesquad.todo.web.works.dto.WorkSaveRequest;
import codesquad.todo.web.works.dto.WorkSaveResponse;
import codesquad.todo.web.works.dto.WorkUpdateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class WorkService {

    public static final String NOT_FOUND_WORK_ERROR = "해당 id의 Work를 찾을 수 없습니다.";

    private final WorkRepository workRepository;

    public WorkSaveResponse workSave(WorkSaveRequest workSaveRequest) {
        Work work = new Work(workSaveRequest.getTitle(),
                workSaveRequest.getContent(),
                workSaveRequest.getAuthor());
        workRepository.save(work);
        log.debug("[SAVE AFTER] : {}", work);
        return new WorkSaveResponse(work.getId());
    }

    public BaseResponse workUpdate(Long id, WorkUpdateRequest workUpdateRequest) {
        Work work = workRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(NOT_FOUND_WORK_ERROR));
        log.debug("[UPDATE BEFORE] : {}", work);
        work.update(workUpdateRequest.getTitle(), workUpdateRequest.getContent());
        workRepository.update(work);
        log.debug("[UPDATE AFTER] : {}", work);
        return BaseResponse.ok();
    }

    public List<Work> findAll() {
        return workRepository.findAll();
    }

}
