package codesquad.todo.web.works;

import codesquad.todo.domain.work.Work;
import codesquad.todo.domain.work.WorkRepository;
import codesquad.todo.web.works.dto.BaseResponse;
import codesquad.todo.web.works.dto.WorkSaveRequest;
import codesquad.todo.web.works.dto.WorkSaveResponse;
import codesquad.todo.web.works.dto.WorkUpdateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/works")
public class WorkController {

    public static final String NOT_FOUND_WORK_ERROR = "해당 id의 Work를 찾을 수 없습니다.";
    private final WorkRepository workRepository;

    @PostMapping
    public ResponseEntity<?> workSave(@RequestBody WorkSaveRequest workSaveRequest) {
        Work work = new Work(workSaveRequest.getTitle(),
                workSaveRequest.getContent(),
                workSaveRequest.getAuthor());
        workRepository.save(work);
        log.debug("[SAVE AFTER] : {}", work);
        return ResponseEntity.ok().body(new WorkSaveResponse(work.getId()));
    }

    @PutMapping("/{id}") // 내용 수정
    public ResponseEntity<? extends BaseResponse> workUpdate(@PathVariable Long id, @RequestBody WorkUpdateRequest workUpdateRequest) {
        Work work = workRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(NOT_FOUND_WORK_ERROR));
        log.debug("[UPDATE BEFORE] : {}", work);
        work.update(workUpdateRequest.getTitle(), workUpdateRequest.getContent());
        workRepository.update(work);
        log.debug("[UPDATE AFTER] : {}", work);
        return new ResponseEntity<>(BaseResponse.ok(), HttpStatus.OK);
    }
}
