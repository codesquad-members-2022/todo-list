package team07.todolist.repository;

import java.util.List;
import team07.todolist.domain.ActivityLog;

public interface ActivityLogRepository {

	ActivityLog save(ActivityLog activityLog);
	List<ActivityLog> findAll();

}
