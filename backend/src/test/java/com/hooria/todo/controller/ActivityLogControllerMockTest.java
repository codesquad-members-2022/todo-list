package com.hooria.todo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hooria.todo.domain.Action;
import com.hooria.todo.domain.ActivityLog;
import com.hooria.todo.domain.Status;
import com.hooria.todo.dto.ActivityLogResponse;
import com.hooria.todo.service.ActivityLogService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ActivityLogController.class)
class ActivityLogControllerMockTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    ActivityLogService activityLogService;

    ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    @Test
    @DisplayName("저장소에 저장되어 있는 전체 활동로그 목록을 읽어 반환한다.")
    void getActivities() throws Exception {

        // given
        LocalDateTime now = LocalDateTime.now();
        List<ActivityLog> activityLogs = List.of(
                ActivityLog.of(1, "userId1", Action.ADD, "taskTitle1", Status.TODO, Status.IN_PROGRESS, now, true),
                ActivityLog.of(2, "userId2", Action.REMOVE, "taskTitle2", Status.IN_PROGRESS, Status.DONE, now, false),
                ActivityLog.of(3, "userId3", Action.UPDATE, "taskTitle3", Status.IN_PROGRESS, Status.IN_PROGRESS, now, true)
        );

        List<ActivityLogResponse> activityLogResponses = activityLogs.stream()
                .map(ActivityLogResponse::from)
                .collect(Collectors.toList());

        given(activityLogService.selectAll()).willReturn(activityLogs);

        // when
        ResultActions resultActions = mvc.perform(get("/api/activities"));

        // then
        resultActions.andExpectAll(
                content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                content().encoding(StandardCharsets.UTF_8),
                content().string(objectMapper.writeValueAsString(activityLogResponses)),
                status().isOk()
        );

        verify(activityLogService).selectAll();
    }

    @Test
    @DisplayName("인자로 주어진 'id'를 가진 활동로그를 저장소에서 찾아 읽음 처리한다(soft-delete).")
    void readActivity() throws Exception {

        // given
        long id = 1;
        given(activityLogService.removeById(id)).willReturn(1 /* 반영된 row 수 */);

        // when
        ResultActions resultActions = mvc.perform(delete("/api/activities/{id}", id));

        // then
        resultActions.andExpectAll(
                content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                content().encoding(StandardCharsets.UTF_8),
                status().isOk()
        );
    }
}
