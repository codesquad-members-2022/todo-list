package com.hooria.todo.controller;

import com.hooria.todo.domain.ActivityLog;
import com.hooria.todo.repository.ActivityLogRepository;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "ActivityLog(활동로그) Controller")
@RestController
@RequestMapping("/api/v1/activities")
@RequiredArgsConstructor
class ActivityLogController {

    private final ActivityLogRepository activityLogRepository;

    @ApiOperation(
            value = "새로운 활동로그 등록",
            notes = "새로운 활동로그를 등록한다.",
            produces = "application/json",
            response = ActivityLog.class
    )
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "activityLog",
                    value = "새로운 활동로그"
            )
    })
    @ApiResponses({
            @ApiResponse(
                    code = 201,
                    message = "등록 성공"
            ),
            @ApiResponse(
                    code = 500,
                    message = "서버 에러"
            )
    })
    @PostMapping
    public ActivityLog writeActivityLog(@RequestBody ActivityLog activityLog) {
        return activityLogRepository.insert(activityLog);
    }

    @ApiOperation(
            value = "모든 활동로그 목록 조회",
            notes = "모든 활동로그 목록을 조회한다.",
            produces = "application/json",
            response = List.class
    )
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "조회 성공"
            ),
            @ApiResponse(
                    code = 500,
                    message = "서버 에러"
            )
    })
    @GetMapping
    public List<ActivityLog> getActivities() {
        return activityLogRepository.findAll();
    }
    
    @ApiOperation(
            value = "id 에 해당하는 활동로그 삭제",
            notes = "id 에 해당하는 활동로그를 삭제한다.",
            produces = "application/json",
            response = Integer.class
    )
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "id",
                    value = "활동로그 아이디"
            )
    })
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "삭제 성공"
            ),
            @ApiResponse(
                    code = 500,
                    message = "서버 에러"
            )
    })
    @DeleteMapping("{id}")
    public int removeActivity(@PathVariable long id) {
        return activityLogRepository.deleteById(id);
    }
}
