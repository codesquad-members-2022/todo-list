package com.hooria.todo.repository;

import com.hooria.todo.domain.ActivityLog;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@JdbcTest
class ActivityLogRepositoryTest {

    ActivityLogRepository repository;

    @Autowired
    ActivityLogRepositoryTest(DataSource dataSource) {
        this.repository = new ActivityLogRepository(dataSource);
    }

    @Test
    @DisplayName("")
    void insertSuccess() {

        // given
        LocalDateTime now = LocalDateTime.now();
        ActivityLog activityLog = new ActivityLog(1, "userId1", 1, 1, 2, now, true);

        // when
        ActivityLog result = repository.insert(activityLog);

        // then
        assertThat(result.getId()).isEqualTo(activityLog.getId());
        assertThat(result.getUserId()).isEqualTo(activityLog.getUserId());
        assertThat(result.getActivityType()).isEqualTo(activityLog.getActivityType());
        assertThat(result.getFromStatus()).isEqualTo(activityLog.getFromStatus());
        assertThat(result.getToStatus()).isEqualTo(activityLog.getToStatus());
        assertThat(result.isReadYn()).isEqualTo(activityLog.isReadYn());
    }

    @Test
    @DisplayName("")
    void findAllSuccess() {

        // given
        LocalDateTime now = LocalDateTime.now();
        List<ActivityLog> members = List.of(
                new ActivityLog(1, "userId1", 1, 1, 2, now, true),
                new ActivityLog(2, "userId2", 2, 2, 3, now, false),
                new ActivityLog(3, "userId3", 3, 1, 2, now, true)
        );

        // when
        List<ActivityLog> results = repository.findAll();

        // then
        assertThat(results.size()).isEqualTo(members.size());

        int idx = 0;
        for (ActivityLog result : results) {
            AssertionsForClassTypes.assertThat(result.getId()).isEqualTo(members.get(idx).getId());
            assertThat(result.getUserId()).isEqualTo(members.get(idx).getUserId());
            assertThat(result.getActivityType()).isEqualTo(members.get(idx).getActivityType());
            assertThat(result.getFromStatus()).isEqualTo(members.get(idx).getFromStatus());
            assertThat(result.getToStatus()).isEqualTo(members.get(idx).getToStatus());
            assertThat(result.isReadYn()).isEqualTo(members.get(idx).isReadYn());
            ++idx;
        }
    }

    @Test
    @DisplayName("")
    void deleteByIdSuccess() {

        // given
        int id = 1;

        // when
        int resultCount = repository.deleteById(id);

        // then
        assertThat(resultCount).isNotEqualTo(0);
    }
}
