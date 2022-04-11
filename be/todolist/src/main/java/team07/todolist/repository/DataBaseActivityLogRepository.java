package team07.todolist.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import team07.todolist.domain.ActivityLog;

@Repository
public class DataBaseActivityLogRepository implements ActivityLogRepository {

	@Override
	public ActivityLog save(ActivityLog activityLog) {
		return null;
	}

	@Override
	public List<ActivityLog> findAll() {
		return null;
	}
}
