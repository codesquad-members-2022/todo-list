package codesquad.todo.web.works;

import codesquad.todo.domain.work.Work;
import codesquad.todo.domain.work.WorkRepository;
import codesquad.todo.web.works.dto.WorkSaveRequest;
import codesquad.todo.web.works.dto.WorkSaveResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/works")
public class WorkController {

    private final WorkRepository workRepository;

    @PostMapping
    public ResponseEntity<?> workSave(@RequestBody WorkSaveRequest workSaveRequest) {
        Work work = new Work(workSaveRequest.getTitle(),
                workSaveRequest.getContent(),
                workSaveRequest.getAuthor());
        workRepository.save(work);

        return ResponseEntity.ok().body(new WorkSaveResponse(work.getId()));
    }

}
