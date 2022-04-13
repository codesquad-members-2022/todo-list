package todo.list.service;

import org.springframework.stereotype.Service;
import todo.list.domain.ActivityLog;
import todo.list.repository.ActivityLogRepository;
import todo.list.service.dto.ActivityLogResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityLogService {

    private final ActivityLogRepository activityLogRepository;

    public ActivityLogService(ActivityLogRepository activityLogRepository) {
        this.activityLogRepository = activityLogRepository;
    }

    public List<ActivityLogResponse> findActivityLogs() {
        List<ActivityLog> activityLogs = activityLogRepository.findAll();
        return activityLogs.stream()
                .map(ActivityLogResponse::new)
                .collect(Collectors.toList());
    }
}
