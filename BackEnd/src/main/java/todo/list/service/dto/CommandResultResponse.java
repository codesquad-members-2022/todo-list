package todo.list.service.dto;

public class CommandResultResponse {
    private int status;
    private CardCommandResponse resources;

    public CommandResultResponse(int status, CardCommandResponse resources) {
        this.status = status;
        this.resources = resources;
    }

    public int getStatus() {
        return status;
    }

    public CardCommandResponse getResources() {
        return resources;
    }
}
