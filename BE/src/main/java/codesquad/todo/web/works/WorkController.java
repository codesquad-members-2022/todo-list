package codesquad.todo.web.works;

import codesquad.todo.domain.work.Work;
import codesquad.todo.service.WorkService;
import codesquad.todo.web.works.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/works")
public class WorkController {

    private final WorkService workService;

    @PostMapping
    public WorkSaveResponse workSave(@RequestBody WorkSaveRequest workSaveRequest) {
        return workService.workSave(workSaveRequest);
    }

    @GetMapping
    public WorkListResponse showAllWorkList() {
        return workService.findAll();
    }

    @PutMapping("/{id}") // 내용 수정
    public WorkUpdateResponse workUpdate(@PathVariable Long id, @RequestBody WorkUpdateRequest workUpdateRequest) {
        return workService.workUpdate(id, workUpdateRequest);
    }
}
