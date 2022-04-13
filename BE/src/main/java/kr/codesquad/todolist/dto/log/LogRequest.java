package kr.codesquad.todolist.dto.log;

import kr.codesquad.todolist.domain.Activity;
import kr.codesquad.todolist.domain.Log;

import java.time.LocalDateTime;

public class LogRequest {

    private Long id;
    private String memberId;
    private String previousSubject;
    private String currentSubject;
    private Integer previousSectionId;
    private Integer currentSectionId;
    private LocalDateTime activatedAt = LocalDateTime.now();
    private Activity activity;

    private LogRequest() {};

    public static LogRequest of(String memberId, String previousSubject, Integer previousSectionId, Activity activity) {
        LogRequest logRequest = new LogRequest();
        logRequest.memberId = memberId;
        logRequest.previousSubject = previousSubject;
        logRequest.previousSectionId = previousSectionId;
        logRequest.activity = activity;

        return logRequest;
    }

    public Log toEntity() {
        return new Log(this.id, this.memberId, this.previousSubject, this.currentSubject, this.previousSectionId, this.currentSectionId, this.activatedAt, this.activity);
    }

    public Long getId() {
        return id;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getPreviousSubject() {
        return previousSubject;
    }

    public String getCurrentSubject() {
        return currentSubject;
    }

    public Integer getPreviousSectionId() {
        return previousSectionId;
    }

    public Integer getCurrentSectionId() {
        return currentSectionId;
    }

    public LocalDateTime getActivatedAt() {
        return activatedAt;
    }

    public Activity getActivity() {
        return activity;
    }
}
