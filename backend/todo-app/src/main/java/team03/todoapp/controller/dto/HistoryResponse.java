package team03.todoapp.controller.dto;

public class HistoryResponse {

    private Long historyId;
    private String actionType;
    private String cardTitle;
    private String pastLocation;
    private String nowLocation;
    private String historyDate;



    public HistoryResponse(Long historyId, String actionType, String cardTitle, String pastLocation,
        String nowLocation, String historyDate) {
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

    public String getHistoryDate() {
        return historyDate;
    }
}
