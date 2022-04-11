package com.hooria.todo.controller;

import com.hooria.todo.dto.ActivityLogResponse;
import com.hooria.todo.service.ActivityLogService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "ActivityLog(활동로그) Controller")
@RestController
@RequestMapping("/api/activities")
@RequiredArgsConstructor
class ActivityLogController {

    private final ActivityLogService activityLogService;

    @ApiOperation(
            value = "모든 활동로그 목록 조회",
            notes = "모든 활동로그 목록을 조회한다.",
            produces = "application/json",
            response = ResponseEntity.class
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
    public ResponseEntity<List<ActivityLogResponse>> getActivities() {
        List<ActivityLogResponse> response = activityLogService.selectAll()
                .stream()
                .map(ActivityLogResponse::from)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(response);
    }
    
    @ApiOperation(
            value = "id 에 해당하는 활동로그 읽음 처리",
            notes = "id 에 해당하는 활동로그를 읽음 처리한다.",
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
                    message = "읽음 처리 성공"
            ),
            @ApiResponse(
                    code = 500,
                    message = "서버 에러"
            )
    })
    @DeleteMapping("{id}")
    public int readActivity(@PathVariable long id) {
        return activityLogService.removeById(id);
    }
}
