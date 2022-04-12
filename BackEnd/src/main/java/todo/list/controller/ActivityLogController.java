package todo.list.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import todo.list.service.dto.ActivityLogResponse;
import todo.list.service.ActivityLogService;

import java.util.List;

@RestController
public class ActivityLogController {

    private final ActivityLogService activityLogService;

    public ActivityLogController(ActivityLogService activityLogService) {
        this.activityLogService = activityLogService;
    }

    @GetMapping("/activity-logs")
    public List<ActivityLogResponse> showActivityLogs() {
        return activityLogService.findActivityLogs();
    }
}
