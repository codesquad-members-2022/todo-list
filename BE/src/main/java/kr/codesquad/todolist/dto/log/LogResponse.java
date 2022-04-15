package kr.codesquad.todolist.dto.log;

import kr.codesquad.todolist.domain.Activity;
import kr.codesquad.todolist.domain.Log;

import java.time.LocalDateTime;

public class LogResponse {

    private Long id;
    private String memberId;
    private String previousSubject;
    private String currentSubject;
    private Integer previousSectionId;
    private Integer currentSectionId;
    private LocalDateTime activatedAt = LocalDateTime.now();
    private String activity;

    public LogResponse(Long id, String memberId, String previousSubject, String currentSubject, Integer previousSectionId,
                       Integer currentSectionId, LocalDateTime activatedAt,  String activity) {
        this.id = id;
        this.memberId = memberId;
        this.previousSubject = previousSubject;
        this.currentSubject = currentSubject;
        this.previousSectionId = previousSectionId;
        this.currentSectionId = currentSectionId;
        this.activatedAt = activatedAt;
        this.activity = activity;
    }

    public static LogResponse from(Log log) {
        return new LogResponse(log.getId(), log.getMemberId(), log.getPreviousSubject(), log.getCurrentSubject(),
                log.getPreviousSectionId(), log.getCurrentSectionId(), log.getActivatedAt(), log.getActivityString());
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

    public String getActivity() {
        return activity;
    }
}
