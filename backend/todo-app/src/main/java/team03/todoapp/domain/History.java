package team03.todoapp.domain;

import org.apache.tomcat.jni.Local;

import java.time.LocalDateTime;

public class History {
    private Long historyId;
    private String actionType;
    private String cardTitle;
    private String pastLocation;
    private String nowLocation;
    private LocalDateTime historyDate;

    public History(Long historyId, String actionType, String cardTitle, String pastLocation, String nowLocation, LocalDateTime historyDate) {
        this.historyId = historyId;
        this.actionType = actionType;
        this.cardTitle = cardTitle;
        this.pastLocation = pastLocation;
        this.nowLocation = nowLocation;
        this.historyDate = historyDate;
    }

    public Long getHistoryId() {
        return historyId;
    }

    public String getActionType() {
        return actionType;
    }

    public String getCardTitle() {
        return cardTitle;
    }

    public String getPastLocation() {
        return pastLocation;
    }

    public String getNowLocation() {
        return nowLocation;
    }

    public LocalDateTime getHistoryDate() {
        return historyDate;
    }
}
