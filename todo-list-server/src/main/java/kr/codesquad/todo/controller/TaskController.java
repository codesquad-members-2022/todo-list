package kr.codesquad.todo.controller;

import kr.codesquad.todo.domain.Task;
import kr.codesquad.todo.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Api(value = "TaskController v1")
public class TaskController {
    TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @ApiOperation(value = "추가", notes = "일 목록에 태스크를 추가합니다.\n 1)태스크 제목(title), 2)태스크 내용(content), 3)작성자 id(author), 4)태스크 상태(status)는 필수값입니다.\n 태스크 상태는 정수 1은 todo, 2는 doing, 3은 done입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "요청이 성공했습니다.", response = Task.class),
            @ApiResponse(code = 400, message = "잘못 된 요청 입니다."),
            @ApiResponse(code = 500, message = "서버에서 발생한 에러입니다.")
    })
    @PostMapping("/task")
    public ResponseEntity<Task> add(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @ApiOperation(value = "전체 조회", notes = "전체 태스크를 DB에서 불러옵니다. \n json에서 정수 1을 key로 하는 task는 TODO, 2는 DOING, 3은 DONE입니다.\n(아래 예시에서의 addtionalProp1의 값이 실제 응답에서는 정수 1로 바뀌게 됩니다.)")
    @ApiResponses({
            @ApiResponse(code = 200, message = "요청이 성공했습니다."),
            @ApiResponse(code = 500, message = "서버에서 발생한 에러입니다.")
    })
    @GetMapping("/task")
    public ResponseEntity<Map<Integer, List<Task>>> load() {
        return taskService.getAllTasks();
    }

    @PatchMapping("/task/status")
    public ResponseEntity<Task> move(@RequestParam long idx, int status) {
        return taskService.changeTaskStatus(idx, status);
    }
}
