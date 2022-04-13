package todo.list.service.dto;

public class CommandResultResponse<T> {
    private int status;
    private T resources;

    public CommandResultResponse(int status, T resources) {
        this.status = status;
        this.resources = resources;
    }

    public int getStatus() {
        return status;
    }

    public T getResources() {
        return resources;
    }
}
