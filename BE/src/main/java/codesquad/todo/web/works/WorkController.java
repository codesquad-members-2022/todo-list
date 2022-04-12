package codesquad.todo.web.works;

import codesquad.todo.domain.user.User;
import codesquad.todo.domain.user.UserRepository;
import codesquad.todo.domain.work.WorkStatus;
import codesquad.todo.service.WorkService;
import codesquad.todo.web.works.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/works")
public class WorkController {

    public static final Long DEFAULT_USER_ID = 1L;
    private final UserRepository userRepository;
    private final WorkService workService;

    @PostMapping
    public WorkSaveResponse workSave(@RequestBody WorkSaveRequest workSaveRequest) {
        User defaultUser = userRepository.findById(DEFAULT_USER_ID).get();
        return workService.workSave(defaultUser, workSaveRequest);
    }

    @PutMapping("/{id}") // 내용 수정
    public WorkUpdateResponse workUpdate(@PathVariable Long id, @RequestBody WorkUpdateRequest workUpdateRequest) {
        return workService.workUpdate(id, workUpdateRequest);
    }

    @PatchMapping("/{id}")
    public WorkMoveResponse moveWork(@PathVariable Long id, @RequestBody WorkMoveRequest workMoveRequest) {
        Integer targetIndex = workMoveRequest.getTargetStatusIndex();
        WorkStatus targetStatus = workMoveRequest.getTargetStatus();
        return workService.workMove(id, targetIndex, targetStatus);
    }

    @GetMapping
    public WorkListResponse workAllList() {
        User defaultUser = userRepository.findById(DEFAULT_USER_ID).get();
        return workService.findWorkList(defaultUser.getId());
    }
}
