package todo.list.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import todo.list.service.ActivityLogDto;
import todo.list.service.ActivityLogService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ActivityLogController {

    private final ActivityLogService activityLogService;

    @GetMapping("/activity-logs")
    public List<ActivityLogDto> showActivityLogs() {
        List<ActivityLogDto> activityLogs = activityLogService.findActivityLogs();
        return activityLogs;
    }
}
