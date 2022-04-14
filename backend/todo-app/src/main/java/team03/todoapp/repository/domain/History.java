package team03.todoapp.repository.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;

public class History {
    private Long historyId;
    private String actionType;
    private String cardTitle;
    private String pastLocation;
    private String nowLocation;
    private LocalDateTime historyDateTime;

    public History(Long historyId, String actionType, String cardTitle, String pastLocation, String nowLocation, LocalDateTime historyDateTime) {
        this.historyId = historyId;
        this.actionType = actionType;
        this.cardTitle = cardTitle;
        this.pastLocation = pastLocation;
        this.nowLocation = nowLocation;
        this.historyDateTime = historyDateTime;
    }

    public History(String actionType, String cardTitle, Optional<String> pastLocation, String nowLocation, LocalDateTime historyDateTime) {
        this(null, actionType, cardTitle, pastLocation.orElse(null), nowLocation, historyDateTime);
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

    public String getHistoryDateTime() {
        return historyDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Override
    public String toString() {
        return "History{" +
            "historyId=" + historyId +
            ", actionType='" + actionType + '\'' +
            ", cardTitle='" + cardTitle + '\'' +
            ", pastLocation='" + pastLocation + '\'' +
            ", nowLocation='" + nowLocation + '\'' +
            ", historyDateTime=" + historyDateTime +
            '}';
    }
}
