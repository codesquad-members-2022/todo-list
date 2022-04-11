package team07.todolist.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;
import team07.todolist.domain.ActivityLog;

@Repository
public class MemoryActivityLogRepository implements ActivityLogRepository {

	private static Map<Long, ActivityLog> store = new ConcurrentHashMap<>();
	private final AtomicLong sequence = new AtomicLong();

	@Override
	public ActivityLog save(ActivityLog activityLog) {
		long id = sequence.incrementAndGet();
		ActivityLog newActivityLog = new ActivityLog(activityLog, id);
		store.put(id, newActivityLog);
		return newActivityLog;
	}

	@Override
	public List<ActivityLog> findAll() {
		return new ArrayList<>(store.values());
	}
}
