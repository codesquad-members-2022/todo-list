package kr.codesquad.todolist.dto.log;

import kr.codesquad.todolist.domain.Activity;
import kr.codesquad.todolist.domain.Card;
import kr.codesquad.todolist.domain.Log;
import kr.codesquad.todolist.dto.card.CardResponse;
import kr.codesquad.todolist.dto.card.CreateCardRequest;
import kr.codesquad.todolist.dto.card.MotionInfo;
import kr.codesquad.todolist.dto.card.UpdateCardRequest;

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

    private LogRequest() {
    }

    public static LogRequest addRequest(CreateCardRequest cardRequest) {
        LogRequest logRequest = new LogRequest();
        logRequest.memberId = cardRequest.getAuthor();
        logRequest.previousSectionId = cardRequest.getSectionId();
        logRequest.previousSubject = cardRequest.getSubject();
        logRequest.activity = Activity.ADD;

        return logRequest;
    }

    public static LogRequest updateRequest(CardResponse currentCard, CardResponse updatedCard) {
        LogRequest logRequest = new LogRequest();
        logRequest.memberId = currentCard.getAuthor();
        logRequest.previousSectionId = currentCard.getSectionId();
        logRequest.previousSubject = currentCard.getSubject();

        if (!currentCard.hasSameSubject(updatedCard.getSubject())) {
            logRequest.currentSubject = updatedCard.getSubject();
        }

        logRequest.activity = Activity.UPDATE;

        return logRequest;
    }

    public static LogRequest moveRequest(CardResponse currentCard, MotionInfo motionInfo) {
        LogRequest logRequest = new LogRequest();
        logRequest.memberId = currentCard.getAuthor();
        logRequest.previousSectionId = currentCard.getSectionId();

        if(!currentCard.isSameSection(motionInfo.getSectionId())) {
            logRequest.currentSectionId = motionInfo.getSectionId();
        }

        logRequest.previousSubject = currentCard.getSubject();
        logRequest.activity = Activity.MOVE;

        return logRequest;
    }

    public static LogRequest deleteRequest(CardResponse currentCard) {
        LogRequest logRequest = new LogRequest();
        logRequest.memberId = currentCard.getAuthor();
        logRequest.previousSectionId = currentCard.getSectionId();
        logRequest.previousSubject = currentCard.getSubject();
        logRequest.activity = Activity.REMOVE;

        return logRequest;
    }

    public Log toEntity() {
        return new Log(this.id, this.memberId, this.previousSubject, this.currentSubject, this.previousSectionId,
                this.currentSectionId, this.activatedAt, this.activity);
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
