package com.hooria.todo.repository;

import com.hooria.todo.domain.Action;
import com.hooria.todo.domain.ActivityLog;
import com.hooria.todo.domain.Status;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@JdbcTest
class ActivityLogRepositoryTest {

    ActivityLogRepository repository;

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Autowired
    ActivityLogRepositoryTest(DataSource dataSource) {
        this.repository = new ActivityLogRepository(dataSource);
    }

    @Test
    @DisplayName("인자로 주어진 활동로그를 저장소에 저장한다.")
    void insertSuccess() {

        // given
        LocalDateTime now = LocalDateTime.now();
        ActivityLog activityLog = ActivityLog.of(1, "userId1", Action.ADD, "taskTitle1", Status.TODO, Status.IN_PROGRESS, now, true);

        // when
        ActivityLog result = repository.insert(activityLog);

        // then
        assertThat(result.getId()).isEqualTo(activityLog.getId());
        assertThat(result.getUserId()).isEqualTo(activityLog.getUserId());
        assertThat(result.getAction()).isEqualTo(activityLog.getAction());
        assertThat(result.getFromStatus()).isEqualTo(activityLog.getFromStatus());
        assertThat(result.getToStatus()).isEqualTo(activityLog.getToStatus());
        assertThat(dateTimeFormatter.format(result.getCreatedAt())).isEqualTo(dateTimeFormatter.format(activityLog.getCreatedAt()));
        assertThat(result.isReadYn()).isEqualTo(activityLog.isReadYn());
    }

    @Test
    @DisplayName("저장소에 저장되어 있는 모든 활동로그를 조회한다.")
    void findAllSuccess() {

        // given
        LocalDateTime now = LocalDateTime.now();
        List<ActivityLog> activityLogs = List.of(
                ActivityLog.of(1, "userId1", Action.ADD, "taskTitle1", Status.TODO, Status.IN_PROGRESS, now, true),
                ActivityLog.of(2, "userId2", Action.REMOVE, "taskTitle2", Status.IN_PROGRESS, Status.DONE, now, false),
                ActivityLog.of(3, "userId3", Action.UPDATE, "taskTitle3", Status.IN_PROGRESS, Status.IN_PROGRESS, now, true)
        );

        // when
        List<ActivityLog> results = repository.findAll();

        // then
        assertThat(results.size()).isEqualTo(activityLogs.size());

        int size = results.size();
        for (int index = 0; index < size; ++index) {
            ActivityLog activityLog1 = results.get(index);
            ActivityLog activityLog2 = activityLogs.get(index);

            assertThat(activityLog1.getId()).isEqualTo(activityLog2.getId());
            assertThat(activityLog1.getUserId()).isEqualTo(activityLog2.getUserId());
            assertThat(activityLog1.getAction()).isEqualTo(activityLog2.getAction());
            assertThat(activityLog1.getFromStatus()).isEqualTo(activityLog2.getFromStatus());
            assertThat(activityLog1.getToStatus()).isEqualTo(activityLog2.getToStatus());
            assertThat(dateTimeFormatter.format(activityLog1.getCreatedAt())).isEqualTo(dateTimeFormatter.format(activityLog2.getCreatedAt()));
            assertThat(activityLog1.isReadYn()).isEqualTo(activityLog2.isReadYn());
        }
    }

    @Test
    @DisplayName("인자로 주어진 'id'를 가진 활동로그를 저장소에서 삭제한다.")
    void deleteByIdSuccess() {

        // given
        int id = 1;

        // when
        int resultCount = repository.deleteById(id);

        // then
        assertThat(resultCount).isNotEqualTo(0);
    }
}
