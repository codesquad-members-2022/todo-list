package todo.list.controller;

import org.springframework.web.bind.annotation.*;
import todo.list.service.dto.ActivityLogRequest;
import todo.list.service.dto.ActivityLogResponse;
import todo.list.service.ActivityLogService;

import java.util.List;

@RestController
@RequestMapping("/activity-logs")
public class ActivityLogController {

    private final ActivityLogService activityLogService;

    public ActivityLogController(ActivityLogService activityLogService) {
        this.activityLogService = activityLogService;
    }

    @GetMapping
    public List<ActivityLogResponse> showActivityLogs() {
        return activityLogService.findActivityLogs();
    }

    @PostMapping
    public void save(@RequestBody ActivityLogRequest activityLogRequest) {
        activityLogService.save(activityLogRequest);
    }
}
