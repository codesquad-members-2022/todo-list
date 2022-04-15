package kr.codesquad.todolist.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Log {

    private final Long id;
    private final String memberId;
    private final String previousSubject;
    private final String currentSubject;
    private final Integer previousSectionId;
    private final Integer currentSectionId;
    private final LocalDateTime activatedAt;
    private final Activity activity;

    public Log(Long id, String memberId, String previousSubject, String currentSubject, Integer previousSectionId, Integer currentSectionId, LocalDateTime activatedAt, Activity activity) {
        this.id = id;
        this.memberId = memberId;
        this.previousSubject = previousSubject;
        this.currentSubject = currentSubject;
        this.previousSectionId = previousSectionId;
        this.currentSectionId = currentSectionId;
        this.activatedAt = activatedAt;
        this.activity = activity;
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

    public String getActivityString() {
        return activity.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Log log = (Log) o;
        return getId().equals(log.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
