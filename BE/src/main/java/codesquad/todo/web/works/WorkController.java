package codesquad.todo.web.works;

import codesquad.todo.domain.work.Work;
import codesquad.todo.service.WorkService;
import codesquad.todo.web.works.dto.BaseResponse;
import codesquad.todo.web.works.dto.WorkSaveRequest;
import codesquad.todo.web.works.dto.WorkSaveResponse;
import codesquad.todo.web.works.dto.WorkUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/works")
public class WorkController {

    private final WorkService workService;

    @PostMapping
    public ResponseEntity<?> workSave(@RequestBody WorkSaveRequest workSaveRequest) {
        WorkSaveResponse workSaveResponse = workService.workSave(workSaveRequest);
        return ResponseEntity.ok().body(workSaveResponse);
    }

    @GetMapping
    public List<Work> showAllWorkList() {
        return workService.findAll();
    }

    @PutMapping("/{id}") // 내용 수정
    public ResponseEntity<? extends BaseResponse> workUpdate(@PathVariable Long id, @RequestBody WorkUpdateRequest workUpdateRequest) {
        BaseResponse workUpdateResponse = workService.workUpdate(id, workUpdateRequest);
        return new ResponseEntity<>(workUpdateResponse, HttpStatus.OK);
    }
}
